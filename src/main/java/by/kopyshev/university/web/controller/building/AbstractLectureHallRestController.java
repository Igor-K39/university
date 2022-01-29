package by.kopyshev.university.web.controller.building;

import by.kopyshev.university.dto.building.LectureHallDTO;
import by.kopyshev.university.dto.building.LectureHallUpdateDTO;
import by.kopyshev.university.service.building.LectureHallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.assureIdConsistent;

public class AbstractLectureHallRestController {
    private static final Logger log = LoggerFactory.getLogger(AbstractCampusController.class);

    private final LectureHallService service;

    protected AbstractLectureHallRestController(LectureHallService service) {
        this.service = service;
    }

    public LectureHallDTO create(LectureHallUpdateDTO lectureHallUpdateDTO) {
        log.info("Creating a new LectureHall from {}", lectureHallUpdateDTO);
        return service.create(lectureHallUpdateDTO);
    }

    public LectureHallDTO get(int id) {
        log.info("Getting LectureHall with id {}", id);
        return service.get(id);
    }

    public LectureHallDTO getByNumber(String campusNumber, String number) {
        log.info("Getting LectureHall of campus {} with number {}", campusNumber, number);
        return service.getByNumber(campusNumber, number);
    }

    public List<LectureHallDTO> getAll() {
        log.info("Getting all LectureHalls");
        return service.getAll();
    }

    public void update(LectureHallUpdateDTO lectureHallUpdateDTO, int id) {
        log.info("Updating LectureHall with id {} by {} ", id, lectureHallUpdateDTO);
        assureIdConsistent(lectureHallUpdateDTO, id);
        service.update(lectureHallUpdateDTO);
    }

    public void delete(int id) {
        log.info("Deleting campus with id {}", id);
        service.delete(id);
    }
}
