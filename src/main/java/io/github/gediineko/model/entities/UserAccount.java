package io.github.gediineko.model.entities;

import io.github.gediineko.model.base.entity.BaseEntity;
import io.github.gediineko.model.ref.RoleType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@ToString(callSuper = true)
public class UserAccount extends BaseEntity implements UserDetails {

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private boolean accountNonExpired = true;

    @Column
    private boolean accountNonLocked = true;

    @Column
    private boolean credentialsNonExpired = true;

    @Column
    private boolean enabled = true;

    @Transient
    private Long initialRole;

    @Transient
    private String newPassword;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "JOIN_USER_ACCOUNT_X_ROLE",
            joinColumns = @JoinColumn(name = "USER_ACCOUNT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(Role::getRoleType)
                .map(RoleType::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
