package by.kopyshev.university.service.education;

import by.kopyshev.university.dto.education.FacultyDepartmentDTO;

import java.util.List;

public interface FacultyDepartmentService {

    FacultyDepartmentDTO create(FacultyDepartmentDTO facultyDepartmentDTO);

    FacultyDepartmentDTO get(int id);

    List<FacultyDepartmentDTO> getAll(Integer facultyId);

    void update(FacultyDepartmentDTO facultyDTO);

    void delete(int id);
}
