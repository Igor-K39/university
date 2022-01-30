package by.kopyshev.university.web.controller.education.speciality;

import by.kopyshev.university.dto.education.SpecialityDTO;
import by.kopyshev.university.service.education.SpecialityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.assureIdConsistent;

public class AbstractSpecialityController {
    private static final Logger log = LoggerFactory.getLogger(AbstractSpecialityController.class);

    private final SpecialityService service;

    public AbstractSpecialityController(SpecialityService service) {
        this.service = service;
    }

    public SpecialityDTO create(SpecialityDTO facultyDTO) {
        log.info("Creating a new Speciality from {}", facultyDTO);
        return service.create(facultyDTO);
    }

    public SpecialityDTO get(int id) {
        log.info("Getting Speciality with id {}", id);
        return service.get(id);
    }

    public List<SpecialityDTO> getAll() {
        log.info("Getting all Faculties");
        return service.getAll();
    }

    public void update(SpecialityDTO facultyDTO, int id) {
        log.info("Updating Speciality with id {} by {} ", id, facultyDTO);
        assureIdConsistent(facultyDTO, id);
        service.update(facultyDTO);
    }

    public void delete(int id) {
        log.info("Deleting Speciality with id {}", id);
        service.delete(id);
    }
}
