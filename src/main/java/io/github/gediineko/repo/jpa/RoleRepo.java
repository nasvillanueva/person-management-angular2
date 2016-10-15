package io.github.gediineko.repo.jpa;

import io.github.gediineko.model.entities.Role;
import io.github.gediineko.model.ref.RoleType;
import io.github.gediineko.repo.base.BaseJpaRepo;

import java.util.List;
import java.util.Optional;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
public interface RoleRepo extends BaseJpaRepo<Role, Long> {

    Optional<Role> findByName(String name);

    List<Role> findByRoleType(RoleType roleType);
}
