package by.kopyshev.university.service.education;

import by.kopyshev.university.domain.education.role.Student;
import by.kopyshev.university.dto.education.student.StudentDTO;
import by.kopyshev.university.dto.education.student.StudentUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.education.StudentMapper;
import by.kopyshev.university.repository.education.StudentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.checkNew;
import static by.kopyshev.university.util.ValidationUtil.checkNotFoundWithId;
import static java.util.Objects.isNull;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper mapper;
    private final StudentRepository repository;

    public StudentServiceImpl(StudentMapper mapper, StudentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public StudentDTO create(StudentUpdateDTO studentUpdateDTO) {
        checkNew(studentUpdateDTO);
        Student saved = repository.save(mapper.toEntity(studentUpdateDTO));
        return mapper.toDTO(saved);
    }

    @Override
    public StudentDTO get(int id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Student.class, "id = " + id));
        return mapper.toDTO(student);
    }

    @Override
    public List<StudentDTO> getAll(Integer studentGroupId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "person.lastName", "person.firstName", "person.middleName");
        List<Student> facultyDepartments = isNull(studentGroupId)
                ? repository.getAll(sort).orElse(List.of())
                : repository.getAll(studentGroupId).orElse(List.of());
        return mapper.toDTO(facultyDepartments);
    }

    @Override
    @Transactional
    public void update(StudentUpdateDTO facultyDepartmentDTO) {
        int id = facultyDepartmentDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(Student.class, "id = " + id));
        repository.save(mapper.toEntity(facultyDepartmentDTO));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Student.class, id);
    }
}
