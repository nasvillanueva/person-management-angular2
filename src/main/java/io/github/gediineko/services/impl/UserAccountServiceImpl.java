package io.github.gediineko.services.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import io.github.gediineko.model.dto.CSVRowDto;
import io.github.gediineko.model.entities.Role;
import io.github.gediineko.model.entities.UserAccount;
import io.github.gediineko.model.ref.RoleType;
import io.github.gediineko.repo.jpa.RoleRepo;
import io.github.gediineko.repo.jpa.UserAccountRepo;
import io.github.gediineko.services.UserAccountService;
import io.github.gediineko.services.UserProfileService;
import io.github.gediineko.services.exceptions.EntityAlreadyExistsException;
import io.github.gediineko.services.exceptions.EntityDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    @Transactional(readOnly = true)
    public UserAccount get(Long id) {
        Optional<UserAccount> userAccount = Optional.of(userAccountRepo.findOne(id));
        if (!userAccount.isPresent()) {
            return null;
        }
        return userAccount.get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserAccount> getAll() {
        return userAccountRepo.findByRoleType(RoleType.ADMIN)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        Optional<UserAccount> existingUser = userAccountRepo.findByUsername(userAccount.getUsername());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        Role role = roleRepo.getOne(userAccount.getInitialRole());

        UserAccount newUserAccount = new UserAccount();

        newUserAccount.setId(null);
        newUserAccount.setUsername(userAccount.getUsername());
        newUserAccount.setPassword(passwordEncoder.encode(userAccount.getNewPassword()));
        newUserAccount.getRoles().add(role);

        return userAccountRepo.save(newUserAccount);
    }

    @Override
    public UserAccount update(UserAccount userAccount) {
        Optional<UserAccount> existingUserOptl = Optional.of(userAccountRepo.getOne(userAccount.getId()));
        if (!existingUserOptl.isPresent()) {
            throw new IllegalArgumentException("User does not exists");
        }
        UserAccount existingUser = existingUserOptl.get();

        if (!existingUser.getPassword().equals(userAccount.getNewPassword())) {
            existingUser.setPassword(passwordEncoder.encode(userAccount.getNewPassword()));
        }

        return userAccountRepo.save(existingUser);
    }

    @Override
    public void uploadUsers(MultipartFile file) throws IOException {
        HeaderColumnNameMappingStrategy<CSVRowDto> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(CSVRowDto.class);

        CsvToBean<CSVRowDto> csvToBean = new CsvToBean<>();
        List<CSVRowDto> csvRowDtos = csvToBean.parse(strategy, new InputStreamReader(file.getInputStream()));

        csvRowDtos.stream()
                .map(CSVRowDto::mapProfile)
                .forEach(userProfile -> {
                    if (userProfile.getId() == null || userProfile.getId() == 0) {
                        try {
                            userProfileService.create(userProfile);
                        } catch (EntityAlreadyExistsException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            userProfileService.update(userProfile);
                        } catch (EntityAlreadyExistsException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void delete(Long userAccountId) {
        UserAccount userAccount = Optional.ofNullable(userAccountRepo.getOne(userAccountId))
                .orElseThrow(() -> new EntityDoesNotExistException(
                        "User with ID Does not exist: " + userAccountId,
                        "error.user.notExists",
                        new Object[]{userAccountId}));

        userAccountRepo.delete(userAccount);
    }

    @Override
    public void addRole(Long userId, Long roleId) {
        UserAccount user = userAccountRepo.findOne(userId);
        Role role = roleRepo.findOne(roleId);
        if (user != null && role != null) {
            user.getRoles().add(role);
        }
    }

    @Override
    public void removeRole(Long userId, Long roleId) {
        UserAccount user = userAccountRepo.findOne(userId);
        Role role = roleRepo.findOne(roleId);
        if (user != null && role != null) {
            user.getRoles().remove(role);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userAccountRepo.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
