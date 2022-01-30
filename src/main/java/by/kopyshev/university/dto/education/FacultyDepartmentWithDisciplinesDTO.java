package by.kopyshev.university.dto.education;

import by.kopyshev.university.dto.education.lecture.DisciplineDTO;

import java.util.List;

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
}
