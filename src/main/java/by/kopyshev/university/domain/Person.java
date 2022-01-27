package by.kopyshev.university.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "person")
@Access(AccessType.FIELD)
public class Person extends BaseEntity {

    @NotBlank
    @Size(min = 2, max = 25)
    @Column(name = "first_name")
    protected String firstName;

    @NotBlank
    @Size(min = 2, max = 25)
    @Column(name = "last_name")
    protected String lastName;

    @NotBlank
    @Size(min = 2, max = 25)
    @Column(name = "middle_name")
    protected String middleName;

    @NotNull
    @Column(name = "birth_date")
    protected LocalDate birthDate;

    @NotBlank
    @Size(min = 2, max = 25)
    @Column(name = "city")
    protected String city;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "address")
    protected String address;

    @NotBlank
    @Size(min = 2, max = 20)
    @Column(name = "passport")
    protected String passport;

    @NotBlank
    @Size(min = 7, max = 20)
    @Column(name = "mobile_phone")
    protected String mobilePhone;

    @Email
    @Size(min = 5, max = 40)
    @Column(name = "email")
    protected String email;

    public Person() {
    }

    public Person(Integer id, String firstName, String lastName, String middleName, LocalDate birthDate,
                  String city, String address, String passport, String mobilePhone, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.city = city;
        this.address = address;
        this.passport = passport;
        this.mobilePhone = mobilePhone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate=" + birthDate +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", passport='" + passport + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
