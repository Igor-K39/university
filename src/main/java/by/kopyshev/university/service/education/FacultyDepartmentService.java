package by.kopyshev.university.service.education;

import by.kopyshev.university.dto.education.FacultyDepartmentDTO;
import by.kopyshev.university.dto.education.FacultyDepartmentWithDisciplinesDTO;

import java.util.List;

public interface FacultyDepartmentService {

    FacultyDepartmentDTO create(FacultyDepartmentDTO facultyDepartmentDTO);

    FacultyDepartmentDTO get(int id);

    FacultyDepartmentWithDisciplinesDTO getWithDisciplines(int id);

    List<FacultyDepartmentDTO> getAll(Integer facultyId);

    List<FacultyDepartmentWithDisciplinesDTO> getAllWithDisciplines(Integer facultyId);

    void update(FacultyDepartmentDTO facultyDTO);

    void delete(int id);
}
