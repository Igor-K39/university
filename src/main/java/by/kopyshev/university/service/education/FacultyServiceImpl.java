package by.kopyshev.university.service.education;

import by.kopyshev.university.domain.education.Faculty;
import by.kopyshev.university.dto.education.FacultyDTO;
import by.kopyshev.university.dto.education.role.FacultyWithDepartmentsDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mappers.education.FacultyMapper;
import by.kopyshev.university.repository.education.FacultyRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.checkNew;
import static by.kopyshev.university.util.ValidationUtil.checkNotFoundWithId;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyMapper mapper;
    private final FacultyRepository repository;

    public FacultyServiceImpl(FacultyMapper mapper, FacultyRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public FacultyDTO create(FacultyDTO facultyDTO) {
        checkNew(facultyDTO);
        Faculty saved = repository.save(mapper.toEntity(facultyDTO));
        return mapper.toDTO(saved);
    }

    @Override
    public FacultyDTO get(int id) {
        Faculty faculty = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Faculty.class, "id = " + id));
        return mapper.toDTO(faculty);
    }

    @Override
    public FacultyWithDepartmentsDTO getWithDepartments(int id) {
        Faculty faculty = repository.getWithDepartments(id)
                .orElseThrow(() -> new NotFoundException(Faculty.class, "id = " + id));
        return mapper.toDTOWithDepartments(faculty);
    }

    @Override
    public List<FacultyDTO> getAll() {
        List<Faculty> faculties = repository.getAll(Sort.by(Sort.Direction.ASC, "name")).orElse(List.of());
        return mapper.toDTO(faculties);
    }

    @Override
    public List<FacultyWithDepartmentsDTO> getAllWithDepartments() {
        List<Faculty> faculties = repository.getAllWithDepartments().orElse(List.of());
        return mapper.toDTOWithDepartments(faculties);
    }

    @Override
    @Transactional
    public void update(FacultyDTO facultyDTO) {
        int id = facultyDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(Faculty.class, "id = " + id));
        repository.save(mapper.toEntity(facultyDTO));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Faculty.class, id);
    }
}
