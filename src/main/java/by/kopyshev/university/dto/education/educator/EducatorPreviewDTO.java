package by.kopyshev.university.dto.education.educator;

import by.kopyshev.university.dto.NamedDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Objects;

public class EducatorPreviewDTO extends NamedDTO {

    @Positive
    private Integer facultyDepartmentId;

    @Positive
    private Integer personId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String position;

    @Positive
    private Integer lectureHallId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String academicDegree;

    @Size(min = 5, max = 20)
    private String phoneNumber;

    public EducatorPreviewDTO() {
    }

    public EducatorPreviewDTO(Integer id, String name, Integer facultyDepartmentId, Integer personId, String position,
                              Integer lectureHallId, String academicDegree, String phoneNumber) {
        super(id, name);
        this.facultyDepartmentId = facultyDepartmentId;
        this.personId = personId;
        this.position = position;
        this.lectureHallId = lectureHallId;
        this.academicDegree = academicDegree;
        this.phoneNumber = phoneNumber;
    }

    public Integer getFacultyDepartmentId() {
        return facultyDepartmentId;
    }

    public void setFacultyDepartmentId(Integer facultyDepartmentId) {
        this.facultyDepartmentId = facultyDepartmentId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
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
        if (!(o instanceof EducatorPreviewDTO)) return false;
        if (!super.equals(o)) return false;
        EducatorPreviewDTO that = (EducatorPreviewDTO) o;
        return Objects.equals(getFacultyDepartmentId(), that.getFacultyDepartmentId())
                && Objects.equals(getPersonId(), that.getPersonId())
                && Objects.equals(getPosition(), that.getPosition())
                && Objects.equals(getLectureHallId(), that.getLectureHallId())
                && Objects.equals(getAcademicDegree(), that.getAcademicDegree())
                && Objects.equals(getPhoneNumber(), that.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFacultyDepartmentId(), getPersonId(),
                getPosition(), getLectureHallId(), getAcademicDegree(), getPhoneNumber());
    }

    @Override
    public String toString() {
        return "EducatorPreviewDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", facultyDepartmentId=" + facultyDepartmentId +
                ", personId=" + personId +
                ", position='" + position + '\'' +
                ", lectureHallId=" + lectureHallId +
                ", academicDegree='" + academicDegree + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
