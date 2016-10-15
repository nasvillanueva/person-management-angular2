package io.github.gediineko.model.entities;

import io.github.gediineko.model.dto.CSVRowDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
@Entity
@Getter
@Setter
public class UserProfile extends UserAccount {

    private static final long serialVersionUID = 7956496017634526540L;

    @Embedded
    private Name name;

    @Embedded
    private Address address;

    @Column
    private LocalDate birthDate;

    @Column
    private Double gwa;

    @Column
    private LocalDate dateHired;

    @Column
    private Boolean currentlyEmployed;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contactInfo = new ArrayList<>();

}
