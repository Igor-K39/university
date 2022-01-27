package by.kopyshev.university.domain.education;

import by.kopyshev.university.domain.NamedEntity;
import by.kopyshev.university.domain.education.lecture.Discipline;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "faculty_department")
@Access(AccessType.FIELD)
public class FacultyDepartment extends NamedEntity {

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "address")
    private String address;

    @NotBlank
    @Size(min = 5, max = 40)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min = 5, max = 20)
    @Column(name = "phone_number")
    private String phoneNumber;

    @Size(max = 1200)
    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "facultyDepartment")
    private List<Discipline> disciplines;

    public FacultyDepartment() {
    }

    public FacultyDepartment(Integer id, String name, String address, String email,
                             String phoneNumber, String description) {
        super(id, name);
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "FacultyDepartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", faculty=" + faculty +
                '}';
    }
}
