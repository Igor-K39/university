package by.kopyshev.university.domain.education;

import by.kopyshev.university.domain.NamedEntity;
import by.kopyshev.university.domain.education.role.Educator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "student_group")
@Access(AccessType.FIELD)
public class StudentGroup extends NamedEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @ManyToOne
    @JoinColumn(name = "study_type_id")
    private StudyType studyType;

    @NotNull
    @Positive
    @Max(8)
    @Column(name = "current_education_year")
    private Integer currentEducationYear;

    @NotNull
    @Column(name = "admission")
    private LocalDate admission;

    @ManyToOne
    @JoinColumn(name = "curator_id")
    private Educator curator;

//    private List<>

    public StudentGroup() {
    }

    public StudentGroup(Integer id, String name, Speciality speciality, Specialization specialization,
                        StudyType studyType, Integer currentEducationYear, LocalDate admission, Educator curator) {
        super(id, name);
        this.speciality = speciality;
        this.specialization = specialization;
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

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
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

    @Override
    public String toString() {
        return "StudentGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speciality=" + speciality +
                ", specialization=" + specialization +
                ", studyType=" + studyType +
                ", currentEducationYear=" + currentEducationYear +
                ", admission=" + admission +
                ", curator=" + curator +
                '}';
    }
}
