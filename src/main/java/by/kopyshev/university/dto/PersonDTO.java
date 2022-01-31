package by.kopyshev.university.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

public class PersonDTO extends BaseDTO {

    @NotBlank
    @Size(min = 2, max = 25)
    protected String firstName;

    @NotBlank
    @Size(min = 2, max = 25)
    protected String lastName;

    @NotBlank
    @Size(min = 2, max = 25)
    protected String middleName;

    @NotNull
    protected LocalDate birthDate;

    @NotBlank
    @Size(min = 2, max = 25)
    protected String city;

    @NotBlank
    @Size(min = 2, max = 100)
    protected String address;

    @NotBlank
    @Size(min = 2, max = 20)
    protected String passport;

    @NotBlank
    @Size(min = 7, max = 20)
    protected String mobilePhone;

    @Email
    @Size(min = 5, max = 40)
    protected String email;

    public PersonDTO() {
    }

    public PersonDTO(Integer id, String firstName, String lastName, String middleName, LocalDate birthDate,
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

    public PersonDTO(PersonDTO personDTO) {
        this(personDTO.id, personDTO.firstName, personDTO.lastName, personDTO.middleName, personDTO.birthDate,
                personDTO.city, personDTO.address, personDTO.passport, personDTO.mobilePhone, personDTO.email);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonDTO)) return false;
        if (!super.equals(o)) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(getFirstName(), personDTO.getFirstName())
                && Objects.equals(getLastName(), personDTO.getLastName())
                && Objects.equals(getMiddleName(), personDTO.getMiddleName())
                && Objects.equals(getBirthDate(), personDTO.getBirthDate())
                && Objects.equals(getCity(), personDTO.getCity())
                && Objects.equals(getAddress(), personDTO.getAddress())
                && Objects.equals(getPassport(), personDTO.getPassport())
                && Objects.equals(getMobilePhone(), personDTO.getMobilePhone())
                && Objects.equals(getEmail(), personDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFirstName(), getLastName(), getMiddleName(), getBirthDate(), getCity(),
                getAddress(), getPassport(), getMobilePhone(), getEmail());
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
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
