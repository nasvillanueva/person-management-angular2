package io.github.gediineko.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
@Embeddable
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Address implements Serializable {

    private static final long serialVersionUID = 1505261939825065200L;

    @Column
    private String streetNumber;

    @Column
    private String barangay;

    @Column
    private String city;

    @Column
    private String zipCode;
}
