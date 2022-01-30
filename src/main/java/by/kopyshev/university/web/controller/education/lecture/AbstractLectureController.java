package by.kopyshev.university.web.controller.education.lecture;

import by.kopyshev.university.dto.education.lecture.LectureDTO;
import by.kopyshev.university.dto.education.lecture.LectureUpdateDTO;
import by.kopyshev.university.service.education.LectureService;
import by.kopyshev.university.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.assureIdConsistent;

public class AbstractLectureController {
    private static final Logger log = LoggerFactory.getLogger(AbstractLectureController.class);

    private final LectureService service;

    protected AbstractLectureController(LectureService service) {
        this.service = service;
    }

    public LectureDTO create(LectureUpdateDTO lectureUpdateDTO) {
        log.info("Creating a new Lecture from {}", lectureUpdateDTO);
        return service.create(lectureUpdateDTO);
    }

    public LectureDTO get(int id) {
        log.info("Getting Lecture with id {}", id);
        return service.get(id);
    }

    public List<LectureDTO> getAllBetween(Integer groupId, LocalDate start, LocalDate end) {
        LocalDate startDate = DateTimeUtil.getMinIfNull(start);
        LocalDate endDate = DateTimeUtil.getMaxIfNull(end);
        log.info("Getting all Lectures of groupId {} between {} and {}", groupId, startDate, endDate);
        return service.getAll(groupId, start, end);
    }

    public void update(LectureUpdateDTO lectureUpdateDTO, int id) {
        log.info("Updating Lecture with id {} by {} ", id, lectureUpdateDTO);
        assureIdConsistent(lectureUpdateDTO, id);
        service.update(lectureUpdateDTO);
    }

    public void delete(int id) {
        log.info("Deleting Lecture with id {}", id);
        service.delete(id);
    }
}
