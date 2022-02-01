package by.kopyshev.university.service.education;

import by.kopyshev.university.AbstractServiceTest;
import by.kopyshev.university.dto.education.lecture.LectureDTO;
import by.kopyshev.university.dto.education.lecture.LectureUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.education.LectureMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static by.kopyshev.university.testdata.LectureConstants.*;
import static by.kopyshev.university.testdata.StudentGroupConstants.STUDENT_GROUP_1_ID;

public class LectureServiceTestImpl extends AbstractServiceTest {

    @Autowired
    LectureService service;

    @Autowired
    LectureMapper mapper;

    @Test
    void create() {
        LectureUpdateDTO lectureUpdate = getNew();
        LectureDTO created = service.create(lectureUpdate);
        lectureUpdate.setId(created.getId());

        LectureDTO expected = mapper.toDTO(mapper.toEntity(lectureUpdate));
        LECTURE_DTO_MATCHER.assertMatch(expected, created);
    }

    @Test
    void get() {
        LectureDTO actual = service.get(LECTURE_1_ID);
        LECTURE_DTO_MATCHER.assertMatch(lecture1DTO, actual);
    }

    @Test
    void getAll() {
        List<LectureDTO> actual = service.getAll(STUDENT_GROUP_1_ID, null, null);
        LECTURE_DTO_MATCHER.assertMatch(List.of(lecture1DTO, lecture5DTO), actual);

        actual = service.getAll(STUDENT_GROUP_1_ID, LocalDate.now(), LocalDate.now());
        LECTURE_DTO_MATCHER.assertMatch(List.of(lecture5DTO), actual);
    }

    @Test
    void update() {
        LectureUpdateDTO lectureUpdate = getUpdated(LECTURE_1_ID);
        service.update(lectureUpdate);
        LECTURE_DTO_MATCHER.assertMatch(mapper.toDTO(mapper.toEntity(lectureUpdate)), service.get(LECTURE_1_ID));
    }

    @Test
    void delete() {
        service.delete(LECTURE_1_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(LECTURE_1_ID));
    }
}
