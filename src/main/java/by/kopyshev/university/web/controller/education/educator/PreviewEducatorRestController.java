package by.kopyshev.university.web.controller.education.educator;

import by.kopyshev.university.dto.education.educator.EducatorPreviewDTO;
import by.kopyshev.university.service.education.EducatorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = PreviewEducatorRestController.PREVIEW_EDUCATOR_REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PreviewEducatorRestController extends AbstractEducatorController {
    public static final String PREVIEW_EDUCATOR_REST_URL = "/api/preview/educators";

    protected PreviewEducatorRestController(EducatorService service) {
        super(service);
    }

    @GetMapping("{id}")
    public EducatorPreviewDTO getPreview(@PathVariable("id") int id) {
        return super.getPreview(id);
    }

    @GetMapping
    public List<EducatorPreviewDTO> getAllPreview(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        return super.getAllPreview(departmentId);
    }
}
