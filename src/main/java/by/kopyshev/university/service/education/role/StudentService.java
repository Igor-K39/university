package by.kopyshev.university.service.education.role;


import by.kopyshev.university.dto.education.role.StudentDTO;
import by.kopyshev.university.dto.education.role.StudentUpdateDTO;

import java.util.List;

public interface StudentService {

    StudentDTO create(StudentUpdateDTO studentUpdateDTO);

    StudentDTO get(int id);

    List<StudentDTO> getAll(Integer facultyDepartmentId);

    void update(StudentUpdateDTO studentDTO);

    void delete(int id);
}
