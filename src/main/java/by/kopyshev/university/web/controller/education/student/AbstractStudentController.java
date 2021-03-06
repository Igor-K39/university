package by.kopyshev.university.web.controller.education.student;

import by.kopyshev.university.dto.education.student.StudentDTO;
import by.kopyshev.university.dto.education.student.StudentPreviewDTO;
import by.kopyshev.university.dto.education.student.StudentUpdateDTO;
import by.kopyshev.university.service.education.StudentService;
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

    public StudentPreviewDTO getPreview(int id) {
        log.info("Getting StudentPreview with id {}", id);
        return service.getPreview(id);
    }

    public List<StudentDTO> getAll(Integer groupId) {
        log.info("Getting all Students of student group {}", groupId);
        return service.getAll(groupId);
    }

    public List<StudentPreviewDTO> getAllPreview(Integer groupId) {
        log.info("Getting all StudentPreview of student group {}", groupId);
        return service.getAllPreview(groupId);
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
