package by.kopyshev.university.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "educator")
@Access(AccessType.FIELD)
public class Educator extends BaseEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "lecture_hall_id")
    private LectureHall lectureHall;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "academic_degree")
    private String academicDegree;

    @Size(min = 5, max = 20)
    @Column(name = "phone_number")
    private String phoneNumber;

    public Educator() {
    }

    public Educator(Integer id, Person person, String position, LectureHall lectureHall,
                    String academicDegree, String phoneNumber) {
        super(id);
        this.person = person;
        this.position = position;
        this.lectureHall = lectureHall;
        this.academicDegree = academicDegree;
        this.phoneNumber = phoneNumber;
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
                ", academicDegree='" + academicDegree + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
