package by.kopyshev.university.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "faculty")
@Access(AccessType.FIELD)
public class Faculty extends NamedEntity {

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "dean_address")
    private String deanAddress;

    @NotBlank
    @Size(min = 5, max = 40)
    @Column(name = "dean_email")
    private String deanEmail;

    @NotBlank
    @Size(min = 5, max = 20)
    @Column(name = "dean_phone_number", nullable = false)
    private String deanPhoneNumber;

    @Size(max = 1200)
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private List<FacultyDepartment> facultyDepartments;

    public Faculty() {
    }

    public Faculty(Integer id, String name, String deanAddress, String deanEmail,
                   String deanPhoneNumber, String description) {
        super(id, name);
        this.deanAddress = deanAddress;
        this.deanEmail = deanEmail;
        this.deanPhoneNumber = deanPhoneNumber;
        this.description = description;
    }

    public String getDeanAddress() {
        return deanAddress;
    }

    public void setDeanAddress(String deanAddress) {
        this.deanAddress = deanAddress;
    }

    public String getDeanEmail() {
        return deanEmail;
    }

    public void setDeanEmail(String deanEmail) {
        this.deanEmail = deanEmail;
    }

    public String getDeanPhoneNumber() {
        return deanPhoneNumber;
    }

    public void setDeanPhoneNumber(String deanPhoneNumber) {
        this.deanPhoneNumber = deanPhoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deanAddress='" + deanAddress + '\'' +
                ", deanEmail='" + deanEmail + '\'' +
                ", deanPhoneNumber='" + deanPhoneNumber + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
