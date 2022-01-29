package by.kopyshev.university.service.building;

import by.kopyshev.university.dto.building.LectureHallDTO;
import by.kopyshev.university.dto.building.LectureHallUpdateDTO;

import java.util.List;

public interface LectureHallService {

    LectureHallDTO create(LectureHallUpdateDTO updateDTO);

    LectureHallDTO get(int id);

    LectureHallDTO getByNumber(String campusNumber, String number);

    List<LectureHallDTO> getAll();

    void update(LectureHallUpdateDTO updateDTO);

    void delete(int id);
}
