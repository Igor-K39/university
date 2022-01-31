package by.kopyshev.university.dto.education;

import by.kopyshev.university.dto.education.lecture.DisciplineDTO;

import java.util.List;
import java.util.Objects;

public class FacultyDepartmentWithDisciplinesDTO extends FacultyDepartmentDTO {

    private List<DisciplineDTO> disciplines;

    public FacultyDepartmentWithDisciplinesDTO() {
    }

    public FacultyDepartmentWithDisciplinesDTO(Integer id, String name, String address, String email, String phoneNumber,
                                               String description, int facultyId, List<DisciplineDTO> disciplines) {
        super(id, name, address, email, phoneNumber, description, facultyId);
        this.disciplines = disciplines;
    }

    public List<DisciplineDTO> getDisciplineDTOs() {
        return disciplines;
    }

    public void setDisciplineDTOs(List<DisciplineDTO> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacultyDepartmentWithDisciplinesDTO)) return false;
        if (!super.equals(o)) return false;
        FacultyDepartmentWithDisciplinesDTO that = (FacultyDepartmentWithDisciplinesDTO) o;
        return Objects.equals(disciplines, that.disciplines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), disciplines);
    }

    @Override
    public String toString() {
        return "FacultyDepartmentWithDisciplinesDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", facultyId=" + facultyId +
                ", disciplines=" + disciplines +
                '}';
    }
}
