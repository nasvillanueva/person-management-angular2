package io.github.gediineko.model.entities;

import io.github.gediineko.model.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
@Entity
@Getter
@Setter
@ToString
public class Contact extends BaseEntity {

    private static final long serialVersionUID = -7365758772604923613L;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "USERPROFILE_ID")
    private UserProfile userProfile;

    @Column
    private String contactInfo;

    @Column
    private String contactType;

}
