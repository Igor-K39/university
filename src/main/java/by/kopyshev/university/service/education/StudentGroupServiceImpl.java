package by.kopyshev.university.service.education;

import by.kopyshev.university.domain.education.StudentGroup;
import by.kopyshev.university.dto.education.group.StudentGroupDTO;
import by.kopyshev.university.dto.education.group.StudentGroupUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.education.StudentGroupMapper;
import by.kopyshev.university.repository.education.StudentGroupRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.checkNew;
import static by.kopyshev.university.util.ValidationUtil.checkNotFoundWithId;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {
    private final StudentGroupMapper mapper;
    private final StudentGroupRepository repository;

    public StudentGroupServiceImpl(StudentGroupMapper mapper, StudentGroupRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public StudentGroupDTO create(StudentGroupUpdateDTO facultyDTO) {
        checkNew(facultyDTO);
        StudentGroup saved = repository.save(mapper.toEntity(facultyDTO));
        return mapper.toDTO(saved);
    }

    @Override
    public StudentGroupDTO get(int id) {
        StudentGroup faculty = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(StudentGroup.class, "id = " + id));
        return mapper.toDTO(faculty);
    }

    @Override
    public List<StudentGroupDTO> getAll() {
        List<StudentGroup> faculties = repository.getAll(Sort.by(Sort.Direction.ASC, "name")).orElse(List.of());
        return mapper.toDTO(faculties);
    }

    @Override
    @Transactional
    public void update(StudentGroupUpdateDTO facultyDTO) {
        int id = facultyDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(StudentGroup.class, "id = " + id));
        repository.save(mapper.toEntity(facultyDTO));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, StudentGroup.class, id);
    }
}
