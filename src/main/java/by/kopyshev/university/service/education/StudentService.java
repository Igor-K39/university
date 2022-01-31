package by.kopyshev.university.service.education;


import by.kopyshev.university.dto.education.student.StudentDTO;
import by.kopyshev.university.dto.education.student.StudentPreviewDTO;
import by.kopyshev.university.dto.education.student.StudentUpdateDTO;

import java.util.List;

public interface StudentService {

    StudentDTO create(StudentUpdateDTO studentUpdateDTO);

    StudentDTO get(int id);

    StudentDTO getByPerson(int personId);

    StudentPreviewDTO getPreview(int id);

    List<StudentDTO> getAll(Integer studentGroupId);

    List<StudentPreviewDTO> getAllPreview(Integer studentGroupId);

    void update(StudentUpdateDTO studentUpdateDTO);

    void delete(int id);
}
