package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.Person;
import by.kopyshev.university.domain.education.StudentGroup;
import by.kopyshev.university.domain.education.role.Student;
import by.kopyshev.university.dto.education.student.StudentDTO;
import by.kopyshev.university.dto.education.student.StudentPreviewDTO;
import by.kopyshev.university.dto.education.student.StudentUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.PersonMapper;
import by.kopyshev.university.repository.PersonRepository;
import by.kopyshev.university.repository.education.StudentGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class StudentMapper {
    private final ModelMapper studentMapper = new ModelMapper();
    private final PersonRepository personRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final PersonMapper personMapper;

    public StudentMapper(PersonRepository personRepository, StudentGroupRepository studentGroupRepository,
                         PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.studentGroupRepository = studentGroupRepository;
        this.personMapper = personMapper;
    }

    @PostConstruct
    public void setup() {
        studentMapper.createTypeMap(StudentUpdateDTO.class, Student.class)
                .addMappings(mapper -> mapper.skip(Student::setPerson))
                .addMappings(mapper -> mapper.skip(Student::setStudentGroup))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();

                    var personId = source.getPersonId();
                    var person = personRepository.findById(source.getPersonId()).orElseThrow(
                            () -> new NotFoundException(Person.class, "id = " + personId));
                    destination.setPerson(person);

                    var studentGroupId = source.getStudentGroupId();
                    var studentGroup = studentGroupRepository.findById(source.getStudentGroupId()).orElseThrow(
                            () -> new NotFoundException(StudentGroup.class, "id = " + studentGroupId));
                    destination.setStudentGroup(studentGroup);
                    return destination;
                });

        studentMapper.createTypeMap(Student.class, StudentDTO.class)
                .addMappings(mapper -> mapper.skip(StudentDTO::setPersonDTO))
                .addMappings(mapper -> mapper.map(s -> s.getStudentGroup().getId(), StudentDTO::setStudentGroupId))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();
                    destination.setPersonDTO(personMapper.toDTO(source.getPerson()));
                    return destination;
                });

        studentMapper.createTypeMap(Student.class, StudentPreviewDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getPerson().getId(), StudentPreviewDTO::setPersonId))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();
                    destination.setName(source.getFullName());
                    destination.setPersonId(source.getPerson().getId());
                    return destination;
                });
    }

    public Student toEntity(StudentUpdateDTO studentUpdateDTO) {
        return isNull(studentUpdateDTO)
                ? null
                : studentMapper.map(studentUpdateDTO, Student.class);
    }

    public StudentDTO toDTO(Student student) {
        return isNull(student)
                ? null
                : studentMapper.map(student, StudentDTO.class);
    }

    public StudentPreviewDTO toPreviewDTO(Student student) {
        return isNull(student)
                ? null
                : studentMapper.map(student, StudentPreviewDTO.class);
    }

    public List<StudentDTO> toDTO(List<Student> students) {
        return isNull(students)
                ? null
                : students.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<StudentPreviewDTO> toPreviewDTO(List<Student> students) {
        return isNull(students)
                ? null
                : students.stream().map(this::toPreviewDTO).collect(Collectors.toList());
    }
}
