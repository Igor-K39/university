package by.kopyshev.university.web.controller.education.role;

import by.kopyshev.university.dto.education.role.EducatorDTO;
import by.kopyshev.university.dto.education.role.EducatorUpdateDTO;
import by.kopyshev.university.service.education.role.EducatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.assureIdConsistent;

public class AbstractEducatorController {
    private static final Logger log = LoggerFactory.getLogger(AbstractEducatorController.class);

    private final EducatorService service;

    protected AbstractEducatorController(EducatorService service) {
        this.service = service;
    }

    public EducatorDTO create(EducatorUpdateDTO educatorUpdateDTO) {
        log.info("Creating a new Educator from {}", educatorUpdateDTO);
        return service.create(educatorUpdateDTO);
    }

    public EducatorDTO get(int id) {
        log.info("Getting Educator with id {}", id);
        return service.get(id);
    }

    public List<EducatorDTO> getAll(Integer facultyDepartmentId) {
        log.info("Getting all Educators of faculty department {}", facultyDepartmentId);
        return service.getAll(facultyDepartmentId);
    }

    public void update(EducatorUpdateDTO educatorDTO, int id) {
        log.info("Updating Educator with id {} by {} ", id, educatorDTO);
        assureIdConsistent(educatorDTO, id);
        service.update(educatorDTO);
    }

    public void delete(int id) {
        log.info("Deleting Educator with id {}", id);
        service.delete(id);
    }
}
