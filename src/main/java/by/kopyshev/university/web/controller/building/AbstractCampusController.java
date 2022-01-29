package by.kopyshev.university.web.controller.building;

import by.kopyshev.university.dto.building.CampusDTO;
import by.kopyshev.university.dto.building.CampusWithHallsDTO;
import by.kopyshev.university.service.building.CampusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.assureIdConsistent;

public abstract class AbstractCampusController {
    private static final Logger log = LoggerFactory.getLogger(AbstractCampusController.class);

    private final CampusService campusService;

    protected AbstractCampusController(CampusService campusService) {
        this.campusService = campusService;
    }

    public CampusDTO create(CampusDTO campusDTO) {
        log.info("Creating a new campus from {}", campusDTO);
        return campusService.create(campusDTO);
    }

    public CampusDTO get(int id) {
        log.info("Getting campus with id {}", id);
        return campusService.get(id);
    }

    public CampusDTO getByNumber(String number) {
        log.info("Getting campus with number {}", number);
        return campusService.getByNumber(number);
    }

    public CampusDTO getWithHalls(int id) {
        log.info("Getting campus including halls with id = {}", id);
        return campusService.getWithHalls(id);
    }

    public List<CampusDTO> getAll() {
        log.info("Getting all campuses");
        return campusService.getAll();
    }

    public List<CampusWithHallsDTO> getAllWithHalls() {
        log.info("Getting all campuses including halls");
        return campusService.getAllWithHalls();
    }

    public void update(CampusDTO campusDTO, int id) {
        log.info("Updating campus with id {} by {} ", id, campusDTO);
        assureIdConsistent(campusDTO, id);
        campusService.update(campusDTO);
    }

    public void delete(int id) {
        log.info("Deleting campus with id {}", id);
        campusService.delete(id);
    }
}
