package by.kopyshev.university.web.controller.education.faculty;

import by.kopyshev.university.dto.education.FacultyDepartmentDTO;
import by.kopyshev.university.service.education.FacultyDepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.assureIdConsistent;

public class AbstractFacultyDepartmentController {
    private static final Logger log = LoggerFactory.getLogger(AbstractFacultyDepartmentController.class);

    private final FacultyDepartmentService service;

    protected AbstractFacultyDepartmentController(FacultyDepartmentService service) {
        this.service = service;
    }

    public FacultyDepartmentDTO create(FacultyDepartmentDTO lectureHallUpdateDTO) {
        log.info("Creating a new FacultyDepartment from {}", lectureHallUpdateDTO);
        return service.create(lectureHallUpdateDTO);
    }

    public FacultyDepartmentDTO get(int id) {
        log.info("Getting FacultyDepartment with id {}", id);
        return service.get(id);
    }

    public List<FacultyDepartmentDTO> getAll(Integer facultyId) {
        log.info("Getting all FacultyDepartments of faculty {}", facultyId);
        return service.getAll(facultyId);
    }

    public void update(FacultyDepartmentDTO lectureHallUpdateDTO, int id) {
        log.info("Updating FacultyDepartment with id {} by {} ", id, lectureHallUpdateDTO);
        assureIdConsistent(lectureHallUpdateDTO, id);
        service.update(lectureHallUpdateDTO);
    }

    public void delete(int id) {
        log.info("Deleting FacultyDepartment with id {}", id);
        service.delete(id);
    }
}
