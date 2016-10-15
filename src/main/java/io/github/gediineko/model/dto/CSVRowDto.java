package io.github.gediineko.model.dto;

import com.opencsv.bean.CsvBind;
import io.github.gediineko.model.entities.Address;
import io.github.gediineko.model.entities.Name;
import io.github.gediineko.model.entities.UserProfile;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by NazIsEvil on 15/10/2016.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CSVRowDto {


    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-mm-dd");

    @CsvBind
    private long id;

    @CsvBind
    private String username;
    @CsvBind
    private String newPassword;

    @CsvBind
    private String title;
    @CsvBind
    private String firstName;
    @CsvBind
    private String middleName;
    @CsvBind
    private String lastName;
    @CsvBind
    private String suffix;


    @CsvBind
    private String birthDate;
    @CsvBind
    private double gwa;
    @CsvBind
    private String dateHired;
    @CsvBind
    private boolean currentlyEmployed;

    @CsvBind
    private String streetNumber;
    @CsvBind
    private String barangay;
    @CsvBind
    private String city;
    @CsvBind
    private String zipCode;

    public CSVRowDto() {
    }

    public CSVRowDto(UserProfile profile){

        this.id = profile.getId();
        this.username = profile.getUsername();
        this.newPassword = profile.getNewPassword();

        this.title = profile.getName().getTitle();
        this.firstName = profile.getName().getFirstName();
        this.middleName = profile.getName().getMiddleName();
        this.lastName = profile.getName().getLastName();
        this.suffix = profile.getName().getSuffix();

        this.birthDate = profile.getBirthDate().format(DTF);
        this.gwa = profile.getGwa();
        this.dateHired = profile.getDateHired().format(DTF);
        this.currentlyEmployed = profile.getCurrentlyEmployed();

        this.streetNumber = profile.getAddress().getStreetNumber();
        this.barangay = profile.getAddress().getBarangay();
        this.city = profile.getAddress().getCity();
        this.zipCode = profile.getAddress().getZipCode();
    }

    public UserProfile mapProfile() {
        UserProfile profile = new UserProfile();
        profile.setId(this.id);
        profile.setUsername(this.username);
        profile.setNewPassword(this.newPassword);

        Name name = new Name(this.firstName, this.middleName, this.lastName,
                this.suffix, this.title);
        profile.setName(name);

        profile.setBirthDate(LocalDate.from(DTF.parse(this.birthDate)));
        profile.setGwa(this.gwa);
        profile.setDateHired(LocalDate.from(DTF.parse(this.dateHired)));
        profile.setCurrentlyEmployed(this.currentlyEmployed);

        Address address = new Address(this.streetNumber, this.barangay,
                this.city, this.zipCode);

        profile.setAddress(address);

        return profile;
    }
}
