package by.kopyshev.university.web.controller.education;

import by.kopyshev.university.dto.education.FacultyDTO;
import by.kopyshev.university.dto.education.role.FacultyWithDepartmentsDTO;
import by.kopyshev.university.service.education.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.assureIdConsistent;

public class AbstractFacultyController {
    private static final Logger log = LoggerFactory.getLogger(AbstractFacultyController.class);

    private final FacultyService service;

    public AbstractFacultyController(FacultyService service) {
        this.service = service;
    }

    public FacultyDTO create(FacultyDTO facultyDTO) {
        log.info("Creating a new Faculty from {}", facultyDTO);
        return service.create(facultyDTO);
    }

    public FacultyDTO get(int id) {
        log.info("Getting Faculty with id {}", id);
        return service.get(id);
    }

    public FacultyWithDepartmentsDTO getWithDepartments(int id) {
        log.info("Getting Faculty including departments with id {}", id);
        return service.getWithDepartments(id);
    }

    public List<FacultyDTO> getAll() {
        log.info("Getting all Faculties");
        return service.getAll();
    }

    public List<FacultyWithDepartmentsDTO> getAllWithDepartments() {
        log.info("Getting all Faculties including departments");
        return service.getAllWithDepartments();
    }

    public void update(FacultyDTO facultyDTO, int id) {
        log.info("Updating Faculty with id {} by {} ", id, facultyDTO);
        assureIdConsistent(facultyDTO, id);
        service.update(facultyDTO);
    }

    public void delete(int id) {
        log.info("Deleting Faculty with id {}", id);
        service.delete(id);
    }
}
