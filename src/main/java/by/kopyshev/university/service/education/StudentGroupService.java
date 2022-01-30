package by.kopyshev.university.service.education;

import by.kopyshev.university.dto.education.group.StudentGroupDTO;
import by.kopyshev.university.dto.education.group.StudentGroupUpdateDTO;
import by.kopyshev.university.dto.education.group.StudentGroupWithStudentsDTO;

import java.util.List;

public interface StudentGroupService {

    StudentGroupDTO create(StudentGroupUpdateDTO facultyDTO);

    StudentGroupDTO get(int id);

    StudentGroupWithStudentsDTO getWithStudents(int id);

    List<StudentGroupDTO> getAll();

    List<StudentGroupWithStudentsDTO> getAllWithStudents();

    void update(StudentGroupUpdateDTO facultyDTO);

    void delete(int id);
}
