package by.kopyshev.university.service.education;


import by.kopyshev.university.dto.education.educator.EducatorDTO;
import by.kopyshev.university.dto.education.educator.EducatorPreviewDTO;
import by.kopyshev.university.dto.education.educator.EducatorUpdateDTO;

import java.util.List;

public interface EducatorService {

    EducatorDTO create(EducatorUpdateDTO educatorUpdateDTO);

    EducatorDTO get(int id);

    EducatorPreviewDTO getPreview(int id);

    List<EducatorDTO> getAll(Integer facultyDepartmentId);

    List<EducatorPreviewDTO> getAllPreview(Integer facultyDepartmentId);

    void update(EducatorUpdateDTO educatorDTO);

    void delete(int id);
}
