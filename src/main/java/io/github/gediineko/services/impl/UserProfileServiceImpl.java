package io.github.gediineko.services.impl;

import io.github.gediineko.model.dto.CSVRowDto;
import io.github.gediineko.model.entities.Contact;
import io.github.gediineko.model.entities.Role;
import io.github.gediineko.model.entities.UserProfile;
import io.github.gediineko.repo.jpa.ContactRepo;
import io.github.gediineko.repo.jpa.RoleRepo;
import io.github.gediineko.repo.jpa.UserProfileRepo;
import io.github.gediineko.services.UserProfileService;
import io.github.gediineko.services.exceptions.EntityAlreadyExistsException;
import io.github.gediineko.services.exceptions.EntityDoesNotExistException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {


    @Autowired
    private UserProfileRepo userProfileRepo;

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserProfile> getAll(String sortProp, String sortDir) {
        return userProfileRepo.findAll()
                .stream()
                .sorted((u1, u2) -> {
                    if (StringUtils.isNotBlank(sortProp) && StringUtils.isNotBlank(sortDir)) {
                        switch (sortProp) {
                            case "gwa":
                                return Double.compare(u1.getGwa(), u2.getGwa()) * (sortDir.equals("asc") ? 1 : -1);
                            case "dateHired":
                                return u1.getDateHired().compareTo(u2.getDateHired()) * (sortDir.equals("asc") ? 1 : -1);
                            case "lastName":
                                return u1.getName().getLastName().compareTo(u2.getName().getLastName()) * (sortDir.equals("asc") ? 1 : -1);
                        }
                    }
                    return Long.compare(u1.getId(), u2.getId());
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserProfile update(UserProfile userProfile) {
        UserProfile existingUser = Optional.ofNullable(userProfileRepo.getOne(userProfile.getId()))
                .orElseThrow(() -> new EntityAlreadyExistsException(
                        "User with that ID Does not exist: " + userProfile.getId(),
                        "error.user.notExists",
                        new Object[]{userProfile.getId()}));

        if(StringUtils.isNotBlank(userProfile.getNewPassword()) && !existingUser.getPassword().equals(userProfile.getNewPassword())){
            existingUser.setPassword(passwordEncoder.encode(userProfile.getNewPassword()));
        }
        return userProfileRepo.save(existingUser);
    }

    @Override
    public UserProfile create(UserProfile userProfile) {
        Optional<UserProfile> existingUser = userProfileRepo.findByUsername(userProfile.getUsername());
        if(existingUser.isPresent()){
            throw new EntityAlreadyExistsException(
                    "User with that username already exists: " + userProfile.getUsername(),
                    "error.user.exists",
                    new Object[]{userProfile.getUsername()});
        }

        UserProfile newUserProfile = new UserProfile();

        newUserProfile.setId(null);
        newUserProfile.setUsername(userProfile.getUsername());
        newUserProfile.setPassword(passwordEncoder.encode(userProfile.getNewPassword()));

        return userProfileRepo.save(newUserProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfile get(Long userId) {
        UserProfile userProfile = Optional.ofNullable(userProfileRepo.findOne(userId))
                .orElseThrow(() -> new EntityDoesNotExistException(
                        "User with that ID does not exist: " + userId,
                        "error.user.notExists",
                        new Object[]{userId}));
        userProfile.setContactInfo(userProfile.getContactInfo());
        return userProfile;
    }

    @Override
    public void addContact(Long userId, Contact contact) {
        UserProfile user = userProfileRepo.findOne(userId);
        if (user != null){
            contact.setUserProfile(user);
            user.getContactInfo().add(contact);
        }
    }

    @Override
    public void removeContact(Long userId, Long contactId) {
        UserProfile user = userProfileRepo.findOne(userId);
        Contact contact = contactRepo.findOne(contactId);
        if (user != null && contact != null) {
            user.getContactInfo().remove(contact);
            userProfileRepo.save(user);
        }
    }

    @Override
    public void updateContact(Contact contact) {
        contact = contactRepo.findOne(contact.getId());
        if (contact != null) {
            contactRepo.save(contact);
        }
    }

    @Override
    public void delete(Long userId) {
        UserProfile user = userProfileRepo.findOne(userId);
        if(user != null){
            userProfileRepo.delete(user);
        }
    }

    @Override
    public CSVRowDto getUpdateFormData(Long userId) {
        UserProfile userProfile = get(userId);
        return new CSVRowDto(userProfile);
    }

    @Override
    public UserProfile uploadForm(CSVRowDto csvRowDto) {
        UserProfile userProfile = csvRowDto.mapProfile();
        return userProfile;
    }

    @Override
    public void addRole(Long userProfileId, Long roleId) {
        UserProfile user = userProfileRepo.findOne(userProfileId);
        Role role = roleRepo.findOne(roleId);
        if (user != null && role != null){
            user.getRoles().add(role);
        }
    }

    @Override
    public void removeRole(Long userProfileId, Long roleId) {
        UserProfile user = userProfileRepo.findOne(userProfileId);
        Role role = roleRepo.findOne(roleId);
        if (user != null && role != null){
            user.getRoles().remove(role);
        }
    }
}
