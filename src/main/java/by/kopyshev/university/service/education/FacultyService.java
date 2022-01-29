package by.kopyshev.university.service.education;

import by.kopyshev.university.dto.education.FacultyDTO;

import java.util.List;

public interface FacultyService {

    FacultyDTO create(FacultyDTO facultyDTO);

    FacultyDTO get(int id);

    List<FacultyDTO> getAll();

    void update(FacultyDTO facultyDTO);

    void delete(int id);
}
