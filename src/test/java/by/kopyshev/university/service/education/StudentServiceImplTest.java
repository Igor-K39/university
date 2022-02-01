package by.kopyshev.university.service.education;

import by.kopyshev.university.AbstractServiceTest;
import by.kopyshev.university.dto.education.student.StudentDTO;
import by.kopyshev.university.dto.education.student.StudentPreviewDTO;
import by.kopyshev.university.dto.education.student.StudentUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.education.StudentMapper;
import by.kopyshev.university.service.PersonService;
import by.kopyshev.university.testdata.PersonConstants;
import by.kopyshev.university.testdata.StudentGroupConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.kopyshev.university.testdata.StudentConstants.*;

class StudentServiceImplTest extends AbstractServiceTest {

    @Autowired
    StudentServiceImpl service;

    @Autowired
    PersonService personService;

    @Autowired
    StudentMapper mapper;

    @Test
    void create() {
        StudentUpdateDTO studentUpdateDTO = getNew(personService.create(PersonConstants.getNew()).id());
        StudentDTO created = service.create(studentUpdateDTO);
        studentUpdateDTO.setId(created.id());

        StudentDTO expected = mapper.toDTO(mapper.toEntity(studentUpdateDTO));
        STUDENT_DTO_MATCHER.assertMatch(expected, created);
    }

    @Test
    void get() {
        StudentDTO actual = service.get(STUDENT_1_ID);
        STUDENT_DTO_MATCHER.assertMatch(student1DTO, actual);
    }

    @Test
    void getByPerson() {
        StudentDTO actual = service.getByPerson(PersonConstants.STUDENT_PERSON_1_ID);
        STUDENT_DTO_MATCHER.assertMatch(student1DTO, actual);
    }

    @Test
    void getPreview() {
        StudentPreviewDTO actual = service.getPreview(STUDENT_1_ID);
        STUDENT_PREVIEW_DTO_MATCHER.assertMatch(studentPreview1DTO, actual);
    }

    @Test
    void getAll() {
        List<StudentDTO> actual = service.getAll(StudentGroupConstants.STUDENT_GROUP_1_ID);
        STUDENT_DTO_MATCHER.assertMatch(List.of(student2DTO, student1DTO), actual);

        actual = service.getAll(null);
        STUDENT_DTO_MATCHER.assertMatch(allStudentsDTO, actual);
    }

    @Test
    void getAllPreview() {
        List<StudentPreviewDTO> actual = service.getAllPreview(StudentGroupConstants.STUDENT_GROUP_1_ID);
        STUDENT_PREVIEW_DTO_MATCHER.assertMatch(List.of(studentPreview2DTO, studentPreview1DTO), actual);

        actual = service.getAllPreview(null);
        STUDENT_PREVIEW_DTO_MATCHER.assertMatch(allStudentsPreviewDTO, actual);
    }

    @Test
    void update() {
        StudentUpdateDTO updated = getUpdated(STUDENT_1_ID, student1DTO.getPersonDTO().getId());
        service.update(updated);
        STUDENT_DTO_MATCHER.assertMatch(mapper.toDTO(mapper.toEntity(updated)), service.get(STUDENT_1_ID));
    }

    @Test
    void delete() {
        service.delete(STUDENT_1_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(STUDENT_1_ID));
    }
}