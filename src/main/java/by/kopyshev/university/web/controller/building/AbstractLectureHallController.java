package by.kopyshev.university.web.controller.building;

import by.kopyshev.university.dto.building.LectureHallDTO;
import by.kopyshev.university.service.building.LectureHallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.assureIdConsistent;

public class AbstractLectureHallController {
    private static final Logger log = LoggerFactory.getLogger(AbstractLectureHallController.class);

    private final LectureHallService service;

    protected AbstractLectureHallController(LectureHallService service) {
        this.service = service;
    }

    public LectureHallDTO create(LectureHallDTO lectureHallDTO) {
        log.info("Creating a new LectureHall from {}", lectureHallDTO);
        return service.create(lectureHallDTO);
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

    public void update(LectureHallDTO lectureHallDTO, int id) {
        log.info("Updating LectureHall with id {} by {} ", id, lectureHallDTO);
        assureIdConsistent(lectureHallDTO, id);
        service.update(lectureHallDTO);
    }

    public void delete(int id) {
        log.info("Deleting LectureHall with id {}", id);
        service.delete(id);
    }
}
