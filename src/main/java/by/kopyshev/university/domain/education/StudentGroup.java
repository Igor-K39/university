package by.kopyshev.university.domain.education;

import by.kopyshev.university.domain.NamedEntity;
import by.kopyshev.university.domain.education.role.Educator;
import by.kopyshev.university.domain.education.role.Student;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@NamedEntityGraph(name = "with-students", includeAllAttributes = true)
@Entity
@Table(name = "student_group")
@Access(AccessType.FIELD)
public class StudentGroup extends NamedEntity {

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @NotNull
    @Column(name = "study_type")
    @Enumerated(EnumType.STRING)
    private StudyType studyType;

    @NotNull
    @Positive
    @Max(8)
    @Column(name = "current_education_year")
    private Integer currentEducationYear;

    @NotNull
    @Column(name = "admission")
    private LocalDate admission;

    @OneToOne
    @JoinColumn(name = "curator_id")
    private Educator curator;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studentGroup")
    private List<Student> students;

    public StudentGroup() {
    }

    public StudentGroup(Integer id, String name, Speciality speciality, StudyType studyType,
                        Integer currentEducationYear, LocalDate admission, Educator curator) {
        super(id, name);
        this.speciality = speciality;
        this.studyType = studyType;
        this.currentEducationYear = currentEducationYear;
        this.admission = admission;
        this.curator = curator;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public StudyType getStudyType() {
        return studyType;
    }

    public void setStudyType(StudyType studyType) {
        this.studyType = studyType;
    }

    public Integer getCurrentEducationYear() {
        return currentEducationYear;
    }

    public void setCurrentEducationYear(Integer currentEducationYear) {
        this.currentEducationYear = currentEducationYear;
    }

    public LocalDate getAdmission() {
        return admission;
    }

    public void setAdmission(LocalDate admission) {
        this.admission = admission;
    }

    public Educator getCurator() {
        return curator;
    }

    public void setCurator(Educator curator) {
        this.curator = curator;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speciality=" + speciality +
                ", studyType=" + studyType +
                ", currentEducationYear=" + currentEducationYear +
                ", admission=" + admission +
                ", curator=" + curator +
                '}';
    }
}
