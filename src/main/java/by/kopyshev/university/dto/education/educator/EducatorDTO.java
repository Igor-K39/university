package by.kopyshev.university.dto.education.educator;

import by.kopyshev.university.dto.BaseDTO;
import by.kopyshev.university.dto.PersonDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

public class EducatorDTO extends BaseDTO {

    @NotNull
    private PersonDTO person;

    @NotBlank
    @Size(min = 2, max = 50)
    private String position;

    @Positive
    private Integer lectureHallId;

    @Positive
    private Integer facultyDepartmentId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String academicDegree;

    @Size(min = 5, max = 20)
    private String phoneNumber;

    public EducatorDTO() {
    }

    public EducatorDTO(Integer id, PersonDTO person, String position, Integer lectureHallId,
                       Integer facultyDepartmentId, String academicDegree, String phoneNumber) {
        super(id);
        this.person = person;
        this.position = position;
        this.lectureHallId = lectureHallId;
        this.facultyDepartmentId = facultyDepartmentId;
        this.academicDegree = academicDegree;
        this.phoneNumber = phoneNumber;
    }

    public EducatorDTO(EducatorDTO educatorDTO) {
        this(educatorDTO.id, educatorDTO.person, educatorDTO.position, educatorDTO.lectureHallId,
                educatorDTO.facultyDepartmentId, educatorDTO.academicDegree, educatorDTO.phoneNumber);
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getLectureHallId() {
        return lectureHallId;
    }

    public void setLectureHallId(Integer lectureHallId) {
        this.lectureHallId = lectureHallId;
    }

    public Integer getFacultyDepartmentId() {
        return facultyDepartmentId;
    }

    public void setFacultyDepartmentId(Integer facultyDepartmentId) {
        this.facultyDepartmentId = facultyDepartmentId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EducatorDTO)) return false;
        if (!super.equals(o)) return false;
        EducatorDTO that = (EducatorDTO) o;
        return Objects.equals(getPerson(), that.getPerson())
                && Objects.equals(getPosition(), that.getPosition())
                && Objects.equals(getLectureHallId(), that.getLectureHallId())
                && Objects.equals(getFacultyDepartmentId(), that.getFacultyDepartmentId())
                && Objects.equals(getAcademicDegree(), that.getAcademicDegree())
                && Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPerson(), getPosition(),
                getLectureHallId(), getFacultyDepartmentId(), getAcademicDegree(), getPhoneNumber());
    }

    @Override
    public String toString() {
        return "EducatorDTO{" +
                "id=" + id +
                ", personDTO=" + person +
                ", position='" + position + '\'' +
                ", lectureHallId=" + lectureHallId +
                ", facultyDepartmentId=" + facultyDepartmentId +
                ", academicDegree='" + academicDegree + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
