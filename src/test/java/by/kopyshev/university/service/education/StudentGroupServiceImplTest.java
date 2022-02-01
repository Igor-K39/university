package by.kopyshev.university.service.education;

import by.kopyshev.university.AbstractServiceTest;
import by.kopyshev.university.dto.education.group.StudentGroupDTO;
import by.kopyshev.university.dto.education.group.StudentGroupUpdateDTO;
import by.kopyshev.university.dto.education.group.StudentGroupWithStudentsDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.education.StudentGroupMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.kopyshev.university.testdata.StudentGroupConstants.*;

class StudentGroupServiceImplTest extends AbstractServiceTest {

    @Autowired
    StudentGroupService service;

    @Autowired
    StudentGroupMapper mapper;

    @Test
    void create() {
        StudentGroupUpdateDTO groupUpdate = getNew();
        StudentGroupDTO created = service.create(groupUpdate);
        groupUpdate.setId(created.id());

        StudentGroupDTO expected = mapper.toDTO(mapper.toEntity(groupUpdate));
        STUDENT_GROUP_DTO_MATCHER.assertMatch(expected, created);
    }

    @Test
    void get() {
        StudentGroupDTO actual = service.get(STUDENT_GROUP_1_ID);
        STUDENT_GROUP_DTO_MATCHER.assertMatch(studentGroup1DTO, actual);
    }

    @Test
    void getWithStudents() {
        StudentGroupWithStudentsDTO actual = service.getWithStudents(STUDENT_GROUP_1_ID);
        GROUP_WITH_STUDENTS_DTO_MATCHER.assertMatch(groupWithStudents1, actual);
    }

    @Test
    void getAll() {
        List<StudentGroupDTO> actual = service.getAll();
        STUDENT_GROUP_DTO_MATCHER.assertMatch(allStudentGroups, actual);
    }

    @Test
    void getAllWithStudents() {
        List<StudentGroupWithStudentsDTO> actual = service.getAllWithStudents();
        GROUP_WITH_STUDENTS_DTO_MATCHER.assertMatch(allWithStudents, actual);
    }

    @Test
    void update() {
        StudentGroupUpdateDTO updated = getUpdated(STUDENT_GROUP_1_ID);
        service.update(updated);
        STUDENT_GROUP_DTO_MATCHER.assertMatch(mapper.toDTO(mapper.toEntity(updated)), service.get(STUDENT_GROUP_1_ID));
    }

    @Test
    void delete() {
        service.delete(STUDENT_GROUP_1_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(STUDENT_GROUP_1_ID));
    }
}