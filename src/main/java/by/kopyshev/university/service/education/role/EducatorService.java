package by.kopyshev.university.service.education.role;


import by.kopyshev.university.dto.education.role.EducatorDTO;
import by.kopyshev.university.dto.education.role.EducatorUpdateDTO;

import java.util.List;

public interface EducatorService {

    EducatorDTO create(EducatorUpdateDTO educatorUpdateDTO);

    EducatorDTO get(int id);

    List<EducatorDTO> getAll(Integer facultyDepartmentId);

    void update(EducatorUpdateDTO educatorDTO);

    void delete(int id);
}
