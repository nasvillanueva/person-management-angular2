package io.github.gediineko.services.util;

import io.github.gediineko.model.entities.Role;
import io.github.gediineko.model.ref.RoleType;

import java.util.Set;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
public class RoleUtil {
    public static boolean isAdmin (Set<Role> roles){
        return roles.stream()
                .map(Role::getRoleType)
                .anyMatch(r -> r.equals(RoleType.ADMIN));

    }
}
