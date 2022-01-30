package by.kopyshev.university.service.education;

import by.kopyshev.university.dto.education.SpecialityDTO;

import java.util.List;

public interface SpecialityService {

    SpecialityDTO create(SpecialityDTO specialityDTO);

    SpecialityDTO get(int id);

    List<SpecialityDTO> getAll();

    void update(SpecialityDTO specialityDTO);

    void delete(int id);
}
