package by.kopyshev.university.service.education;

import by.kopyshev.university.dto.education.lecture.DisciplineDTO;

import java.util.List;

public interface DisciplineService {

    DisciplineDTO create(DisciplineDTO disciplineDTO);

    DisciplineDTO get(int id);

    List<DisciplineDTO> getAll(Integer departmentId);

    void update(DisciplineDTO disciplineDTO);

    void delete(int id);
}
