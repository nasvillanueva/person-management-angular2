package io.github.gediineko.model.entities;

import io.github.gediineko.model.base.entity.BaseEntity;
import io.github.gediineko.model.ref.RoleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
@Entity
@Getter
@Setter
@ToString
public class Role extends BaseEntity {

    private static final long serialVersionUID = 7811599094033445905L;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "JOIN_USER_ACCOUNT_X_ROLE",
            joinColumns = @JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ACCOUNT_ID"))
    private List<UserAccount> userAccounts;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

}
