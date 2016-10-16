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

}
