package by.kopyshev.university.service.education;

import by.kopyshev.university.domain.education.lecture.Discipline;
import by.kopyshev.university.dto.education.lecture.DisciplineDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.education.DisciplineMapper;
import by.kopyshev.university.repository.education.DisciplineRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.checkNew;
import static by.kopyshev.university.util.ValidationUtil.checkNotFoundWithId;
import static java.util.Objects.isNull;

@Service
public class DisciplineServiceImpl implements DisciplineService {
    private final DisciplineMapper mapper;
    private final DisciplineRepository repository;

    public DisciplineServiceImpl(DisciplineMapper mapper, DisciplineRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public DisciplineDTO create(DisciplineDTO disciplineDTO) {
        checkNew(disciplineDTO);
        Discipline saved = repository.save(mapper.toEntity(disciplineDTO));
        return mapper.toDTO(saved);
    }

    @Override
    public DisciplineDTO get(int id) {
        Discipline facultyDepartment = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Discipline.class, "id = " + id));
        return mapper.toDTO(facultyDepartment);
    }

    @Override
    public List<DisciplineDTO> getAll(Integer departmentId) {
        List<Discipline> facultyDepartments = isNull(departmentId)
                ? repository.getAll().orElse(List.of())
                : repository.getAll(departmentId).orElse(List.of());
        return mapper.toDTO(facultyDepartments);
    }

    @Override
    @Transactional
    public void update(DisciplineDTO disciplineDTO) {
        int id = disciplineDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(Discipline.class, "id = " + id));
        repository.save(mapper.toEntity(disciplineDTO));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Discipline.class, id);
    }
}
