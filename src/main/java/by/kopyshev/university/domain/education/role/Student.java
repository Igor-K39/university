package by.kopyshev.university.domain.education.role;

import by.kopyshev.university.domain.BaseEntity;
import by.kopyshev.university.domain.education.StudentGroup;
import by.kopyshev.university.domain.Person;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
@Access(AccessType.FIELD)
public class Student extends BaseEntity {

    @NotBlank
    @Size(min = 6, max = 15)
    @Column(name = "record_book_number")
    private String recordBookNumber;

    @NotNull
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_group_id")
    private StudentGroup studentGroup;

    @Column(name = "leader")
    private boolean leader = false;

    public Student() {
    }

    public Student(Integer id, String recordBookNumber, Person person, StudentGroup studentGroup, boolean leader) {
        super(id);
        this.recordBookNumber = recordBookNumber;
        this.person = person;
        this.studentGroup = studentGroup;
        this.leader = leader;
    }

    public String getRecordBookNumber() {
        return recordBookNumber;
    }

    public void setRecordBookNumber(String recordBookNumber) {
        this.recordBookNumber = recordBookNumber;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public boolean isLeader() {
        return leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }

    public String getFullName() {
        return person.getFirstName() + " " + person.getLastName();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", recordBookNumber='" + recordBookNumber + '\'' +
                ", person=" + person +
                ", studentGroup=" + studentGroup +
                ", leader=" + leader +
                '}';
    }
}
