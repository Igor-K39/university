package by.kopyshev.university.service.education;

import by.kopyshev.university.dto.education.SpecialityDTO;

import java.util.List;

public interface SpecialityService {

    SpecialityDTO create(SpecialityDTO facultyDTO);

    SpecialityDTO get(int id);

    List<SpecialityDTO> getAll();

    void update(SpecialityDTO facultyDTO);

    void delete(int id);
}
