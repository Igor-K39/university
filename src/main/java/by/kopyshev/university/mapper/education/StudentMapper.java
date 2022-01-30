package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.education.role.Student;
import by.kopyshev.university.dto.education.student.StudentDTO;
import by.kopyshev.university.dto.education.student.StudentUpdateDTO;
import by.kopyshev.university.mapper.PersonMapper;
import by.kopyshev.university.repository.PersonRepository;
import by.kopyshev.university.repository.education.StudentGroupRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

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
        Converter<StudentUpdateDTO, Student> toEntityPostConverter = ctx -> {
            StudentUpdateDTO source = ctx.getSource();
            Student destination = ctx.getDestination();
            destination.setPerson(personRepository.getById(source.getPersonId()));
            destination.setStudentGroup(studentGroupRepository.getById(source.getStudentGroupId()));
            return destination;
        };
        studentMapper.createTypeMap(StudentUpdateDTO.class, Student.class)
                .addMappings(mapper -> mapper.skip(Student::setPerson))
                .addMappings(mapper -> mapper.skip(Student::setStudentGroup))
                .setPostConverter(toEntityPostConverter);

        Converter<Student, StudentDTO> toDTOPostConverter = ctx -> {
            Student source = ctx.getSource();
            StudentDTO destination = ctx.getDestination();
            destination.setPersonDTO(personMapper.toDTO(source.getPerson()));
            destination.setStudentGroupId(source.getStudentGroup().getId());
            return destination;
        };
        studentMapper.createTypeMap(Student.class, StudentDTO.class)
                .addMappings(mapper -> mapper.skip(StudentDTO::setPersonDTO))
                .addMappings(mapper -> mapper.skip(StudentDTO::setStudentGroupId))
                .setPostConverter(toDTOPostConverter);
    }

    public Student toEntity(StudentUpdateDTO studentUpdateDTO) {
        return studentMapper.map(studentUpdateDTO, Student.class);
    }

    public StudentDTO toDTO(Student student) {
        return studentMapper.map(student, StudentDTO.class);
    }

    public List<StudentDTO> toDTO(List<Student> students) {
        return students.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
