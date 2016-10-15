package io.github.gediineko.services.impl;

import io.github.gediineko.model.entities.Role;
import io.github.gediineko.model.ref.RoleType;
import io.github.gediineko.repo.jpa.RoleRepo;
import io.github.gediineko.services.RoleService;
import io.github.gediineko.services.exceptions.EntityAlreadyExistsException;
import io.github.gediineko.services.exceptions.EntityDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Set<Role> getAll() {
        return roleRepo.findAll()
                .stream()
                .sorted((r1,r2) -> Long.compare(r1.getId(), r2.getId()))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Set<Role> getAllByType(RoleType roleType) {
        return roleRepo.findByRoleType(roleType)
                .stream()
                .sorted((r1,r2) -> Long.compare(r1.getId(), r2.getId()))
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Role create(Role role) {

        Optional<Role> existingRole = roleRepo.findByName(role.getName());
        if(existingRole.isPresent()){
            throw new EntityAlreadyExistsException(
                    "Role with that name already exists: " + role.getName(),
                    "error.role.exists",
                    new Object[]{role.getName()});
        }

        Role newRole = new Role();

        newRole.setId(null);

        return newRole;
    }

    @Override
    public Role update(Role role) {

        Role existingRole = Optional.ofNullable(roleRepo.getOne(role.getId()))
                .orElseThrow(() -> new EntityDoesNotExistException(
                        "Role with ID Does not exist: " + role.getId(),
                        "error.role.notExists",
                        new Object[]{role.getId()}));

        return roleRepo.save(existingRole);
    }

    @Override
    public void delete(Long roleId) {

        Role existingRole = Optional.ofNullable(roleRepo.getOne(roleId))
                .orElseThrow(() -> new EntityDoesNotExistException(
                        "Role with ID Does not exist: " + roleId,
                        "error.role.notExists",
                        new Object[]{roleId}));

        roleRepo.delete(existingRole);
    }
}
