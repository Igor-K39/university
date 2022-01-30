package by.kopyshev.university.service.education;

import by.kopyshev.university.dto.education.group.StudentGroupDTO;
import by.kopyshev.university.dto.education.group.StudentGroupUpdateDTO;

import java.util.List;

public interface StudentGroupService {

    StudentGroupDTO create(StudentGroupUpdateDTO facultyDTO);

    StudentGroupDTO get(int id);

    List<StudentGroupDTO> getAll();

    void update(StudentGroupUpdateDTO facultyDTO);

    void delete(int id);
}
