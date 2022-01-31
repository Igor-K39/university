package by.kopyshev.university.web.controller;

import by.kopyshev.university.domain.User;
import by.kopyshev.university.dto.education.lecture.LectureDTO;
import by.kopyshev.university.dto.education.student.StudentDTO;
import by.kopyshev.university.service.education.LectureService;
import by.kopyshev.university.service.education.StudentService;
import by.kopyshev.university.web.SecurityUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = ProfileRestController.PROFILE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestController {
    public static final String PROFILE_REST_URL = "/api/profile";

    private final LectureService lectureService;
    private final StudentService studentService;

    public ProfileRestController(LectureService lectureService, StudentService studentService) {
        this.lectureService = lectureService;
        this.studentService = studentService;
    }

    @GetMapping
    public Map<String, Object> userProfile() {
        Map<String, Object> response = new HashMap<>();
        User user = SecurityUtil.get();
        response.put("user", user);
        putLecturesIfExist(user, response, LocalDate.now(), LocalDate.now());
        return response;
    }

    private void putLecturesIfExist(User user, Map<String, Object> response, LocalDate start, LocalDate end) {
        StudentDTO student = studentService.getByPerson(user.getId());
        List<LectureDTO> lectures = lectureService.getAll(student.getStudentGroupId(), start, end);
        if (!lectures.isEmpty()) {
            response.put("lectures", lectures);
        }
    }
}
