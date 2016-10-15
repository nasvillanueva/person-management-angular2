package io.github.gediineko.services;

import io.github.gediineko.model.entities.Role;
import io.github.gediineko.model.ref.RoleType;

import java.util.Set;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
public interface RoleService {

    Set<Role> getAll();
    Set<Role> getAllByType(RoleType roleType);
    Role create(Role role);
    Role update(Role role);
    void delete(Long roleId);
}
