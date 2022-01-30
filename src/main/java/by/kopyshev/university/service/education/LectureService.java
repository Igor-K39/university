package by.kopyshev.university.service.education;


import by.kopyshev.university.dto.education.lecture.LectureDTO;
import by.kopyshev.university.dto.education.lecture.LectureUpdateDTO;

import java.time.LocalDate;
import java.util.List;

public interface LectureService {

    LectureDTO create(LectureUpdateDTO studentUpdateDTO);

    LectureDTO get(int id);

    List<LectureDTO> getAll(Integer studentGroupId, LocalDate start, LocalDate end);

    void update(LectureUpdateDTO studentDTO);

    void delete(int id);
}
