package by.kopyshev.university.dto.education.lecture;

import by.kopyshev.university.dto.NamedDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class DisciplineDTO extends NamedDTO {

    @NotBlank
    @Size(min = 2, max = 20)
    private String shortName;

    private Integer facultyDepartmentId;

    public DisciplineDTO() {
    }

    public DisciplineDTO(Integer id, String name, String shortName, Integer facultyDepartmentId) {
        super(id, name);
        this.shortName = shortName;
        this.facultyDepartmentId = facultyDepartmentId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getFacultyDepartmentId() {
        return facultyDepartmentId;
    }

    public void setFacultyDepartmentId(Integer facultyDepartmentId) {
        this.facultyDepartmentId = facultyDepartmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DisciplineDTO)) return false;
        if (!super.equals(o)) return false;
        DisciplineDTO that = (DisciplineDTO) o;
        return Objects.equals(shortName, that.shortName) && Objects.equals(facultyDepartmentId, that.facultyDepartmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shortName, facultyDepartmentId);
    }

    @Override
    public String toString() {
        return "DisciplineDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", facultyDepartmentId=" + facultyDepartmentId +
                '}';
    }
}
