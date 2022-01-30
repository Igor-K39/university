package by.kopyshev.university.domain.education.lecture;

import by.kopyshev.university.domain.BaseEntity;
import by.kopyshev.university.domain.building.LectureHall;
import by.kopyshev.university.domain.education.StudentGroup;
import by.kopyshev.university.domain.education.role.Educator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedEntityGraph(name = "all-attributes",
        attributeNodes = {
            @NamedAttributeNode(value = "discipline", subgraph = "lecture.discipline"),
                @NamedAttributeNode(value = "lectureHall", subgraph = "lecture.lectureHall"),
                @NamedAttributeNode(value = "educator", subgraph = "lecture.educator"),
                @NamedAttributeNode(value = "studentGroup")
        },
        subgraphs = {
                @NamedSubgraph(name = "lecture.discipline",
                attributeNodes = {
                        @NamedAttributeNode(value = "shortName"),
                        @NamedAttributeNode(value = "facultyDepartment")
                }),
                @NamedSubgraph(name = "lecture.lectureHall",
                attributeNodes = {
                        @NamedAttributeNode(value = "campus"),
                        @NamedAttributeNode(value = "number")
                }),
                @NamedSubgraph(name = "lecture.educator",
                attributeNodes = {
                        @NamedAttributeNode(value = "person"),
                        @NamedAttributeNode(value = "lectureHall"),
                        @NamedAttributeNode(value = "facultyDepartment", subgraph = "department.faculty")
                }),
                @NamedSubgraph(name = "department.faculty",
                attributeNodes = {
                        @NamedAttributeNode(value = "faculty")
                })
        })
@Entity
@Table(name = "lecture")
@Access(AccessType.FIELD)
public class Lecture extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lecture_type")
    private LectureType lectureType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "lecture_hall_id")
    private LectureHall lectureHall;

    @ManyToOne
    @JoinColumn(name = "educator_id")
    private Educator educator;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_group_id")
    private StudentGroup studentGroup;

    @NotNull
    @Column(name = "date")
    private LocalDate date;

    public Lecture() {
    }

    public Lecture(Integer id, Discipline discipline, LectureType lectureType, LectureHall lectureHall,
                   Educator educator, StudentGroup studentGroup, LocalDate date) {
        super(id);
        this.discipline = discipline;
        this.lectureType = lectureType;
        this.lectureHall = lectureHall;
        this.educator = educator;
        this.studentGroup = studentGroup;
        this.date = date;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public LectureType getLectureType() {
        return lectureType;
    }

    public void setLectureType(LectureType lectureType) {
        this.lectureType = lectureType;
    }

    public LectureHall getLectureHall() {
        return lectureHall;
    }

    public void setLectureHall(LectureHall lectureHall) {
        this.lectureHall = lectureHall;
    }

    public Educator getEducator() {
        return educator;
    }

    public void setEducator(Educator educator) {
        this.educator = educator;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", discipline=" + discipline +
                ", lectureType=" + lectureType +
                ", lectureHall=" + lectureHall +
                ", educator=" + educator +
                ", studentGroup=" + studentGroup +
                ", date=" + date +
                '}';
    }
}
