package by.kopyshev.university.dto.education.group;

import by.kopyshev.university.domain.education.StudyType;
import by.kopyshev.university.dto.NamedDTO;
import by.kopyshev.university.dto.education.SpecialityDTO;
import by.kopyshev.university.dto.education.educator.EducatorDTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Objects;

public class StudentGroupDTO extends NamedDTO {

    protected SpecialityDTO speciality;

    @NotNull
    protected StudyType studyType;

    @NotNull
    @Positive
    @Max(8)
    protected Integer currentEducationYear;

    @NotNull
    protected LocalDate admission;

    protected EducatorDTO curator;

    public StudentGroupDTO() {
    }

    public StudentGroupDTO(Integer id, String name, SpecialityDTO speciality, StudyType studyType,
                           Integer currentEducationYear, LocalDate admission, EducatorDTO curator) {
        super(id, name);
        this.speciality = speciality;
        this.studyType = studyType;
        this.currentEducationYear = currentEducationYear;
        this.admission = admission;
        this.curator = curator;
    }

    public SpecialityDTO getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityDTO specialityId) {
        this.speciality = specialityId;
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

    public EducatorDTO getCurator() {
        return curator;
    }

    public void setCurator(EducatorDTO curator) {
        this.curator = curator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentGroupDTO)) return false;
        if (!super.equals(o)) return false;
        StudentGroupDTO that = (StudentGroupDTO) o;
        return Objects.equals(speciality, that.speciality)
                && getStudyType() == that.getStudyType()
                && Objects.equals(getCurrentEducationYear(), that.getCurrentEducationYear())
                && Objects.equals(getAdmission(), that.getAdmission())
                && Objects.equals(getCurator(), that.getCurator());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speciality, getStudyType(),
                getCurrentEducationYear(), getAdmission(), getCurator());
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
