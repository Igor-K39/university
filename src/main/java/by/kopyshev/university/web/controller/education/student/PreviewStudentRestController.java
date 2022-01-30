package by.kopyshev.university.web.controller.education.student;

import by.kopyshev.university.dto.education.student.StudentPreviewDTO;
import by.kopyshev.university.service.education.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = PreviewStudentRestController.PREVIEW_STUDENT_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PreviewStudentRestController extends AbstractStudentController {
    public static final String PREVIEW_STUDENT_REST_URL = "/api/preview/students";

    protected PreviewStudentRestController(StudentService service) {
        super(service);
    }

    @GetMapping("{id}")
    public StudentPreviewDTO getPreview(@PathVariable("id") int id) {
        return super.getPreview(id);
    }

    @GetMapping
    public List<StudentPreviewDTO> getAllPreview(@RequestParam(value = "group", required = false) Integer groupId) {
        return super.getAllPreview(groupId);
    }
}
