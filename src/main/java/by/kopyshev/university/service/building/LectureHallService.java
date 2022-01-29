package by.kopyshev.university.service.building;

import by.kopyshev.university.dto.building.LectureHallDTO;

import java.util.List;

public interface LectureHallService {

    LectureHallDTO create(LectureHallDTO updateDTO);

    LectureHallDTO get(int id);

    LectureHallDTO getByNumber(String campusNumber, String number);

    List<LectureHallDTO> getAll();

    void update(LectureHallDTO updateDTO);

    void delete(int id);
}
