package by.kopyshev.university.service.education;


import by.kopyshev.university.dto.education.student.StudentDTO;
import by.kopyshev.university.dto.education.student.StudentUpdateDTO;

import java.util.List;

public interface StudentService {

    StudentDTO create(StudentUpdateDTO studentUpdateDTO);

    StudentDTO get(int id);

    List<StudentDTO> getAll(Integer facultyDepartmentId);

    void update(StudentUpdateDTO studentDTO);

    void delete(int id);
}
