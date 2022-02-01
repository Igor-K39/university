package by.kopyshev.university.service.education;

import by.kopyshev.university.AbstractServiceTest;
import by.kopyshev.university.dto.education.educator.EducatorDTO;
import by.kopyshev.university.dto.education.educator.EducatorPreviewDTO;
import by.kopyshev.university.dto.education.educator.EducatorUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.education.EducatorMapper;
import by.kopyshev.university.service.PersonService;
import by.kopyshev.university.testdata.EducatorConstants;
import by.kopyshev.university.testdata.PersonConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static by.kopyshev.university.testdata.EducatorConstants.*;
import static by.kopyshev.university.testdata.FacultyDepartmentConstants.FACULTY_DEPARTMENT_1_ID;

class EducatorServiceImplTest extends AbstractServiceTest {

    @Autowired
    EducatorService service;

    @Autowired
    EducatorMapper mapper;

    @Autowired
    PersonService personService;

    @Test
    void create() {
        EducatorUpdateDTO educatorUpdate = getNew(personService.create(PersonConstants.getNew()).id());
        EducatorDTO created = service.create(educatorUpdate);
        educatorUpdate.setId(created.getId());

        EducatorDTO expected = mapper.toDTO(mapper.toEntity(educatorUpdate));
        EDUCATOR_DTO_MATCHER.assertMatch(expected, created);
    }

    @Test
    void get() {
        EducatorDTO actual = service.get(EDUCATOR_1_ID);
        EDUCATOR_DTO_MATCHER.assertMatch(educator1DTO, actual);
    }

    @Test
    void getPreview() {
        EducatorPreviewDTO actual = service.getPreview(EDUCATOR_1_ID);
        EDUCATOR_PREVIEW_DTO_MATCHER.assertMatch(mapper.toPreviewDTO(educator1DTO), actual);
    }

    @Test
    void getAll() {
        service.getAll(null).forEach(System.out::println);
        List<EducatorDTO> actual = service.getAll(FACULTY_DEPARTMENT_1_ID);
        EDUCATOR_DTO_MATCHER.assertMatch(List.of(educator1DTO), actual);

        actual = service.getAll(null);
        EDUCATOR_DTO_MATCHER.assertMatch(List.of(educator3DTO, educator4DTO, educator1DTO, educator2DTO), actual);
    }

    @Test
    void getAllPreview() {
        List<EducatorPreviewDTO> actual = service.getAllPreview(FACULTY_DEPARTMENT_1_ID);
        EDUCATOR_PREVIEW_DTO_MATCHER.assertMatch(mapper.dtoToPreviewDTO(List.of(educator1DTO)), actual);

        actual = service.getAllPreview(null);
        EDUCATOR_PREVIEW_DTO_MATCHER.assertMatch(
                mapper.dtoToPreviewDTO(List.of(educator3DTO, educator4DTO, educator1DTO, educator2DTO)), actual);
    }

    @Test
    void update() {
        EducatorUpdateDTO updated = EducatorConstants.getUpdated(EDUCATOR_1_ID, educator1DTO.getPerson().id());
        service.update(updated);
        EDUCATOR_DTO_MATCHER.assertMatch(mapper.toDTO(mapper.toEntity(updated)), service.get(EDUCATOR_1_ID));
    }

    @Test
    void delete() {
        service.delete(EDUCATOR_1_ID);
        Assertions.assertThrows(NotFoundException.class, () -> service.get(EDUCATOR_1_ID));
    }
}