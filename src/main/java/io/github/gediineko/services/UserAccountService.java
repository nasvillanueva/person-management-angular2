package io.github.gediineko.services;

import io.github.gediineko.model.entities.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
public interface UserAccountService extends UserDetailsService {

    UserAccount get(Long id);

    List<UserAccount> getAll();

    UserAccount create(UserAccount userAccount);

    UserAccount update(UserAccount userAccount);

    void uploadUsers(MultipartFile file) throws IOException;

    void delete(Long userAccountId);

    void addRole(Long userId, Long roleId);

    void removeRole(Long userId, Long roleId);
}
