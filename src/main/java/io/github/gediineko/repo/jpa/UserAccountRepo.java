package io.github.gediineko.repo.jpa;

import io.github.gediineko.model.entities.UserAccount;
import io.github.gediineko.model.ref.RoleType;
import io.github.gediineko.repo.base.BaseJpaRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
public interface UserAccountRepo extends BaseJpaRepo<UserAccount, Long> {

    Optional<UserAccount> findByUsername(String s);

    @Query("select distinct u from UserAccount u inner join u.roles r where r.roleType = :roleType")
    List<UserAccount> findByRoleType(@Param("roleType") RoleType roleType);
}
