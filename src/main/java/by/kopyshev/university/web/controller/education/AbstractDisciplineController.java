package by.kopyshev.university.web.controller.education;

import by.kopyshev.university.dto.education.DisciplineDTO;
import by.kopyshev.university.service.education.DisciplineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.assureIdConsistent;

public class AbstractDisciplineController {
    private static final Logger log = LoggerFactory.getLogger(AbstractDisciplineController.class);

    private final DisciplineService service;

    protected AbstractDisciplineController(DisciplineService service) {
        this.service = service;
    }

    public DisciplineDTO create(DisciplineDTO lectureHallUpdateDTO) {
        log.info("Creating a new Discipline from {}", lectureHallUpdateDTO);
        return service.create(lectureHallUpdateDTO);
    }

    public DisciplineDTO get(int id) {
        log.info("Getting Discipline with id {}", id);
        return service.get(id);
    }

    public List<DisciplineDTO> getAll(Integer departmentId) {
        log.info("Getting all Disciplines of faculty {}", departmentId);
        return service.getAll(departmentId);
    }

    public void update(DisciplineDTO lectureHallUpdateDTO, int id) {
        log.info("Updating Discipline with id {} by {} ", id, lectureHallUpdateDTO);
        assureIdConsistent(lectureHallUpdateDTO, id);
        service.update(lectureHallUpdateDTO);
    }

    public void delete(int id) {
        log.info("Deleting Discipline with id {}", id);
        service.delete(id);
    }
}
