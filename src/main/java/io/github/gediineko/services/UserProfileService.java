package io.github.gediineko.services;

import io.github.gediineko.model.dto.CSVRowDto;
import io.github.gediineko.model.entities.Contact;
import io.github.gediineko.model.entities.UserProfile;

import java.util.List;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
public interface UserProfileService {

    List<UserProfile> getAll(String sortProp, String sortDir);

    UserProfile update(UserProfile userProfile);

    UserProfile create(UserProfile userProfile);

    UserProfile get(Long userId);

    void addContact(Long userId, Contact contact);

    void removeContact(Long userId, Long contactId);

    void updateContact(Contact contact);

    void delete(Long userId);

    CSVRowDto getUpdateFormData(Long userId);

    UserProfile uploadForm(CSVRowDto csvRowDto);

    void addRole(Long userProfileId, Long roleId);

    void removeRole(Long userProfileId, Long roleId);
}
