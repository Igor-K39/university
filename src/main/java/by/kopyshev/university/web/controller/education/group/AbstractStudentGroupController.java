package by.kopyshev.university.web.controller.education.group;

import by.kopyshev.university.dto.education.group.StudentGroupDTO;
import by.kopyshev.university.dto.education.group.StudentGroupUpdateDTO;
import by.kopyshev.university.dto.education.group.StudentGroupWithStudentsDTO;
import by.kopyshev.university.service.education.StudentGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.assureIdConsistent;

public class AbstractStudentGroupController {
    private static final Logger log = LoggerFactory.getLogger(AbstractStudentGroupController.class);

    private final StudentGroupService service;

    public AbstractStudentGroupController(StudentGroupService service) {
        this.service = service;
    }

    public StudentGroupDTO create(StudentGroupUpdateDTO studentGroupUpdateDTO) {
        log.info("Creating a new StudentGroup from {}", studentGroupUpdateDTO);
        return service.create(studentGroupUpdateDTO);
    }

    public StudentGroupDTO get(int id) {
        log.info("Getting StudentGroup with id {}", id);
        return service.get(id);
    }

    public StudentGroupDTO getWithStudent(int id) {
        log.info("Getting StudentGroup with id {} including students", id);
        return service.getWithStudents(id);
    }

    public List<StudentGroupDTO> getAll() {
        log.info("Getting all StudentGroups");
        return service.getAll();
    }

    public List<StudentGroupWithStudentsDTO> getAllWithStudents() {
        log.info("Getting all StudentGroups including students");
        return service.getAllWithStudents();
    }

    public void update(StudentGroupUpdateDTO studentGroupUpdateDTO, int id) {
        log.info("Updating StudentGroup with id {} by {} ", id, studentGroupUpdateDTO);
        assureIdConsistent(studentGroupUpdateDTO, id);
        service.update(studentGroupUpdateDTO);
    }

    public void delete(int id) {
        log.info("Deleting StudentGroup with id {}", id);
        service.delete(id);
    }
}
