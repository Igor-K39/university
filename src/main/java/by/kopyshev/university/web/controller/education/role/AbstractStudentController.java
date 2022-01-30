package by.kopyshev.university.web.controller.education.role;

import by.kopyshev.university.dto.education.role.StudentDTO;
import by.kopyshev.university.dto.education.role.StudentUpdateDTO;
import by.kopyshev.university.service.education.role.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.assureIdConsistent;

public class AbstractStudentController {
    private static final Logger log = LoggerFactory.getLogger(AbstractStudentController.class);

    private final StudentService service;

    protected AbstractStudentController(StudentService service) {
        this.service = service;
    }

    public StudentDTO create(StudentUpdateDTO studentUpdateDTO) {
        log.info("Creating a new Student from {}", studentUpdateDTO);
        return service.create(studentUpdateDTO);
    }

    public StudentDTO get(int id) {
        log.info("Getting Student with id {}", id);
        return service.get(id);
    }

    public List<StudentDTO> getAll(Integer facultyDepartmentId) {
        log.info("Getting all Students of faculty department {}", facultyDepartmentId);
        return service.getAll(facultyDepartmentId);
    }

    public void update(StudentUpdateDTO studentDTO, int id) {
        log.info("Updating Student with id {} by {} ", id, studentDTO);
        assureIdConsistent(studentDTO, id);
        service.update(studentDTO);
    }

    public void delete(int id) {
        log.info("Deleting Student with id {}", id);
        service.delete(id);
    }
}
