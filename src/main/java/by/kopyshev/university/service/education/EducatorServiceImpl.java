package by.kopyshev.university.service.education;

import by.kopyshev.university.domain.education.role.Educator;
import by.kopyshev.university.dto.education.educator.EducatorDTO;
import by.kopyshev.university.dto.education.educator.EducatorPreviewDTO;
import by.kopyshev.university.dto.education.educator.EducatorUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.education.EducatorMapper;
import by.kopyshev.university.repository.education.EducatorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.checkNew;
import static by.kopyshev.university.util.ValidationUtil.checkNotFoundWithId;
import static java.util.Objects.isNull;

@Service
public class EducatorServiceImpl implements EducatorService {
    private final EducatorMapper mapper;
    private final EducatorRepository repository;

    public EducatorServiceImpl(EducatorMapper mapper, EducatorRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public EducatorDTO create(EducatorUpdateDTO educatorUpdateDTO) {
        checkNew(educatorUpdateDTO);
        Educator saved = repository.save(mapper.toEntity(educatorUpdateDTO));
        return mapper.toDTO(saved);
    }

    @Override
    public EducatorDTO get(int id) {
        return mapper.toDTO(getById(id));
    }

    @Override
    public EducatorPreviewDTO getPreview(int id) {
        return mapper.toPreviewDTO(getById(id));
    }

    @Override
    public List<EducatorDTO> getAll(Integer facultyDepartmentId) {
        return mapper.toDTO(getAllByDepartment(facultyDepartmentId));
    }

    @Override
    public List<EducatorPreviewDTO> getAllPreview(Integer facultyDepartmentId) {
        return mapper.toPreviewDTO(getAllByDepartment(facultyDepartmentId));
    }

    @Override
    @Transactional
    public void update(EducatorUpdateDTO educatorUpdateDTO) {
        int id = educatorUpdateDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(Educator.class, "id = " + id));
        repository.save(mapper.toEntity(educatorUpdateDTO));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Educator.class, id);
    }

    private Educator getById(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(Educator.class, "id = " + id));
    }

    private List<Educator> getAllByDepartment(Integer facultyDepartmentId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "person.lastName", "person.firstName", "person.middleName");
        return isNull(facultyDepartmentId)
                ? repository.getAll(sort).orElse(List.of())
                : repository.getAll(facultyDepartmentId).orElse(List.of());
    }
}
