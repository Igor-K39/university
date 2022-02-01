package by.kopyshev.university.domain.education.role;

import by.kopyshev.university.domain.BaseEntity;
import by.kopyshev.university.domain.building.LectureHall;
import by.kopyshev.university.domain.Person;
import by.kopyshev.university.domain.education.FacultyDepartment;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NamedEntityGraph(name = "educator-with-all-fields", attributeNodes = {
        @NamedAttributeNode("person"),
        @NamedAttributeNode("lectureHall"),
        @NamedAttributeNode("facultyDepartment")
})
@Entity
@Table(name = "educator")
@Access(AccessType.FIELD)
public class Educator extends BaseEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "lecture_hall_id")
    private LectureHall lectureHall;

    @ManyToOne
    @JoinColumn(name = "faculty_department_id")
    private FacultyDepartment facultyDepartment;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "academic_degree")
    private String academicDegree;

    @Size(min = 5, max = 20)
    @Column(name = "phone_number")
    private String phoneNumber;

    public Educator() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LectureHall getLectureHall() {
        return lectureHall;
    }

    public void setLectureHall(LectureHall lectureHall) {
        this.lectureHall = lectureHall;
    }

    public FacultyDepartment getFacultyDepartment() {
        return facultyDepartment;
    }

    public void setFacultyDepartment(FacultyDepartment facultyDepartment) {
        this.facultyDepartment = facultyDepartment;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Educator{" +
                "id=" + id +
                ", person=" + person +
                ", position='" + position + '\'' +
                ", lectureHall=" + lectureHall +
                ", facultyDepartment=" + facultyDepartment +
                ", academicDegree='" + academicDegree + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
