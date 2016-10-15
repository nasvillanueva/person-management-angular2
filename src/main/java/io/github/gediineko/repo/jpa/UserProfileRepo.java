package io.github.gediineko.repo.jpa;

import io.github.gediineko.model.entities.UserProfile;
import io.github.gediineko.repo.base.BaseJpaRepo;

import java.util.Optional;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
public interface UserProfileRepo extends BaseJpaRepo<UserProfile, Long> {

    Optional<UserProfile> findByUsername(String username);
}
