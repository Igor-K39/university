package by.kopyshev.university.service.education;

import by.kopyshev.university.domain.education.FacultyDepartment;
import by.kopyshev.university.dto.education.FacultyDepartmentDTO;
import by.kopyshev.university.dto.education.FacultyDepartmentWithDisciplinesDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.education.FacultyDepartmentMapper;
import by.kopyshev.university.repository.education.FacultyDepartmentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.checkNew;
import static by.kopyshev.university.util.ValidationUtil.checkNotFoundWithId;
import static java.util.Objects.isNull;

@Service
public class FacultyDepartmentServiceImpl implements FacultyDepartmentService {
    private final FacultyDepartmentMapper mapper;
    private final FacultyDepartmentRepository repository;

    public FacultyDepartmentServiceImpl(FacultyDepartmentMapper mapper, FacultyDepartmentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public FacultyDepartmentDTO create(FacultyDepartmentDTO facultyDepartmentDTO) {
        checkNew(facultyDepartmentDTO);
        FacultyDepartment saved = repository.save(mapper.toEntity(facultyDepartmentDTO));
        return mapper.toDTO(saved);
    }

    @Override
    public FacultyDepartmentDTO get(int id) {
        FacultyDepartment facultyDepartment = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(FacultyDepartment.class, "id = " + id));
        return mapper.toDTO(facultyDepartment);
    }

    @Override
    public FacultyDepartmentWithDisciplinesDTO getWithDisciplines(int id) {
        FacultyDepartment facultyDepartment = repository.getWithDisciplines(id)
                .orElseThrow(() -> new NotFoundException(FacultyDepartment.class, "id = " + id));
        return mapper.toDTOWithDisciplines(facultyDepartment);
    }

    @Override
    public List<FacultyDepartmentDTO> getAll(Integer facultyId) {
        List<FacultyDepartment> facultyDepartments = isNull(facultyId)
                ? repository.getAll().orElse(List.of())
                : repository.getAll(facultyId).orElse(List.of());
        return mapper.toDTO(facultyDepartments);
    }

    @Override
    public List<FacultyDepartmentWithDisciplinesDTO> getAllWithDisciplines(Integer facultyId) {
        List<FacultyDepartment> facultyDepartments = isNull(facultyId)
                ? repository.getAllWithDisciplines().orElse(List.of())
                : repository.getAllByFacultyWithDisciplines(facultyId).orElse(List.of());
        return mapper.toDTOWithDisciplines(facultyDepartments);
    }

    @Override
    @Transactional
    public void update(FacultyDepartmentDTO facultyDepartmentDTO) {
        int id = facultyDepartmentDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(FacultyDepartment.class, "id = " + id));
        repository.save(mapper.toEntity(facultyDepartmentDTO));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, FacultyDepartment.class, id);
    }
}
