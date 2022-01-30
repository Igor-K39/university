package by.kopyshev.university.dto.education;

import by.kopyshev.university.domain.education.StudyType;
import by.kopyshev.university.dto.NamedDTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Objects;

public class StudentGroupUpdateDTO extends NamedDTO {

    private Integer specialityId;

    @NotNull
    private StudyType studyType;

    @NotNull
    @Positive
    @Max(8)
    private Integer currentEducationYear;

    @NotNull
    private LocalDate admission;

    private Integer curatorId;

    public StudentGroupUpdateDTO() {
    }

    public StudentGroupUpdateDTO(Integer id, String name, Integer specialityId, StudyType studyType,
                                 Integer currentEducationYear, LocalDate admission, Integer curatorId) {
        super(id, name);
        this.specialityId = specialityId;
        this.studyType = studyType;
        this.currentEducationYear = currentEducationYear;
        this.admission = admission;
        this.curatorId = curatorId;
    }

    public Integer getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Integer specialityId) {
        this.specialityId = specialityId;
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

    public Integer getCuratorId() {
        return curatorId;
    }

    public void setCuratorId(Integer curatorId) {
        this.curatorId = curatorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentGroupUpdateDTO)) return false;
        if (!super.equals(o)) return false;
        StudentGroupUpdateDTO that = (StudentGroupUpdateDTO) o;
        return Objects.equals(specialityId, that.specialityId)
                && getStudyType() == that.getStudyType()
                && Objects.equals(getCurrentEducationYear(), that.getCurrentEducationYear())
                && Objects.equals(getAdmission(), that.getAdmission())
                && Objects.equals(getCuratorId(), that.getCuratorId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), specialityId, getStudyType(),
                getCurrentEducationYear(), getAdmission(), getCuratorId());
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", speciality=" + specialityId +
                ", studyType=" + studyType +
                ", currentEducationYear=" + currentEducationYear +
                ", admission=" + admission +
                ", curator=" + curatorId +
                '}';
    }
}
