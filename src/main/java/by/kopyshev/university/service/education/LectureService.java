package by.kopyshev.university.service.education;


import by.kopyshev.university.dto.education.lecture.LectureDTO;
import by.kopyshev.university.dto.education.lecture.LectureUpdateDTO;

import java.time.LocalDate;
import java.util.List;

public interface LectureService {

    LectureDTO create(LectureUpdateDTO lectureUpdateDTO);

    LectureDTO get(int id);

    List<LectureDTO> getAll(Integer studentGroupId, LocalDate start, LocalDate end);

    void update(LectureUpdateDTO lectureUpdateDTO);

    void delete(int id);
}
