package io.github.gediineko.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class Name implements Serializable {

    private static final long serialVersionUID = 3820741391285765813L;

    @Column
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @Column
    private String suffix;

    @Column
    private String title;
}
