package by.kopyshev.university.service.education.role;

import by.kopyshev.university.domain.education.role.Educator;
import by.kopyshev.university.dto.education.role.EducatorDTO;
import by.kopyshev.university.dto.education.role.EducatorUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.education.role.EducatorMapper;
import by.kopyshev.university.repository.education.role.EducatorRepository;
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
        Educator educator = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Educator.class, "id = " + id));
        return mapper.toDTO(educator);
    }

    @Override
    public List<EducatorDTO> getAll(Integer facultyDepartmentId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "last_name", "first_name", "middle_name");
        List<Educator> facultyDepartments = isNull(facultyDepartmentId)
                ? repository.getAll(Sort.by(Sort.Direction.ASC, "person.")).orElse(List.of())
                : repository.getAll(facultyDepartmentId).orElse(List.of());
        return mapper.toDTO(facultyDepartments);
    }

    @Override
    @Transactional
    public void update(EducatorUpdateDTO facultyDepartmentDTO) {
        int id = facultyDepartmentDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(Educator.class, "id = " + id));
        repository.save(mapper.toEntity(facultyDepartmentDTO));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Educator.class, id);
    }
}
