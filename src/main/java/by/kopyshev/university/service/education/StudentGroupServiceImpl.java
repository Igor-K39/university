package by.kopyshev.university.service.education;

import by.kopyshev.university.domain.education.StudentGroup;
import by.kopyshev.university.dto.education.group.StudentGroupDTO;
import by.kopyshev.university.dto.education.group.StudentGroupUpdateDTO;
import by.kopyshev.university.dto.education.group.StudentGroupWithStudentsDTO;
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
    public StudentGroupDTO create(StudentGroupUpdateDTO studentGroupUpdateDTO) {
        checkNew(studentGroupUpdateDTO);
        StudentGroup saved = repository.save(mapper.toEntity(studentGroupUpdateDTO));
        return mapper.toDTO(saved);
    }

    @Override
    public StudentGroupDTO get(int id) {
        StudentGroup group = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(StudentGroup.class, "id = " + id));
        return mapper.toDTO(group);
    }

    @Override
    public StudentGroupWithStudentsDTO getWithStudents(int id) {
        StudentGroup group = repository.getWithStudents(id)
                .orElseThrow(() -> new NotFoundException(StudentGroup.class, "id = " + id));
        return mapper.toDTOWithStudents(group);
    }

    @Override
    public List<StudentGroupDTO> getAll() {
        List<StudentGroup> groups = repository.getAll().orElse(List.of());
        return mapper.toDTO(groups);
    }

    @Override
    public List<StudentGroupWithStudentsDTO> getAllWithStudents() {
        List<StudentGroup> groups = repository.getAllWithStudents().orElse(List.of());
        return mapper.toDTOWithStudents(groups);
    }

    @Override
    @Transactional
    public void update(StudentGroupUpdateDTO studentGroupUpdateDTO) {
        int id = studentGroupUpdateDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(StudentGroup.class, "id = " + id));
        repository.save(mapper.toEntity(studentGroupUpdateDTO));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, StudentGroup.class, id);
    }
}
