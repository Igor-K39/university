package by.kopyshev.university.service.education;

import by.kopyshev.university.dto.education.FacultyDTO;
import by.kopyshev.university.dto.education.role.FacultyWithDepartmentsDTO;

import java.util.List;

public interface FacultyService {

    FacultyDTO create(FacultyDTO facultyDTO);

    FacultyDTO get(int id);

    FacultyWithDepartmentsDTO getWithDepartments(int id);

    List<FacultyDTO> getAll();

    List<FacultyWithDepartmentsDTO> getAllWithDepartments();

    void update(FacultyDTO facultyDTO);

    void delete(int id);
}
