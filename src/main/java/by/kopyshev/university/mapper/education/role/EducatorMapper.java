package by.kopyshev.university.mapper.education.role;

import by.kopyshev.university.domain.Person;
import by.kopyshev.university.domain.building.LectureHall;
import by.kopyshev.university.domain.education.FacultyDepartment;
import by.kopyshev.university.domain.education.role.Educator;
import by.kopyshev.university.dto.education.role.EducatorDTO;
import by.kopyshev.university.dto.education.role.EducatorUpdateDTO;
import by.kopyshev.university.repository.PersonRepository;
import by.kopyshev.university.repository.building.LectureHallRepository;
import by.kopyshev.university.repository.education.FacultyDepartmentRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EducatorMapper {
    private final ModelMapper educatorMapper = new ModelMapper();
    private final PersonRepository personRepository;
    private final LectureHallRepository lectureHallRepository;
    private final FacultyDepartmentRepository facultyDepartmentRepository;

    public EducatorMapper(PersonRepository personRepository, LectureHallRepository lectureHallRepository, 
                          FacultyDepartmentRepository facultyDepartmentRepository) {
        this.personRepository = personRepository;
        this.lectureHallRepository = lectureHallRepository;
        this.facultyDepartmentRepository = facultyDepartmentRepository;
    }

    @PostConstruct
    public void setup() {
        Converter<EducatorUpdateDTO, Educator> toEntityPostConverter = ctx -> {
            EducatorUpdateDTO source = ctx.getSource();
            Person person = personRepository.getById(source.getPersonId());
            LectureHall lectureHall = lectureHallRepository.getById(source.getLectureHallId());
            FacultyDepartment facultyDepartment = facultyDepartmentRepository.getById(source.getFacultyDepartmentId());
            
            Educator destination = ctx.getDestination();
            destination.setPerson(person);
            destination.setLectureHall(lectureHall);
            destination.setFacultyDepartment(facultyDepartment);
            System.out.println(destination);
            return destination;
        };
        educatorMapper.createTypeMap(EducatorUpdateDTO.class, Educator.class)
                .addMappings(mapper -> mapper.skip(Educator::setPerson))
                .addMappings(mapper -> mapper.skip(Educator::setLectureHall))
                .addMappings(mapper -> mapper.skip(Educator::setFacultyDepartment))
                .setPostConverter(toEntityPostConverter);

        Converter<Educator, EducatorDTO> toDTOPostConverter = ctx -> {
            Educator source = ctx.getSource();
            EducatorDTO destination = ctx.getDestination();
            destination.setLectureHallId(source.getLectureHall().getId());
            destination.setFacultyDepartmentId(source.getFacultyDepartment().getId());
            return destination;
        };
        educatorMapper.createTypeMap(Educator.class, EducatorDTO.class)
                .addMappings(mapper -> mapper.skip(EducatorDTO::setLectureHallId))
                .addMappings(mapper -> mapper.skip(EducatorDTO::setFacultyDepartmentId))
                .setPostConverter(toDTOPostConverter);
    }

    public Educator toEntity(EducatorUpdateDTO educatorUpdateDTO) {
        return educatorMapper.map(educatorUpdateDTO, Educator.class);
    }

    public EducatorDTO toDTO(Educator educator) {
        return educatorMapper.map(educator, EducatorDTO.class);
    }

    public List<EducatorDTO> toDTO(List<Educator> educators) {
        return educators.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
