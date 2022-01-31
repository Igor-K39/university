package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.Person;
import by.kopyshev.university.domain.building.LectureHall;
import by.kopyshev.university.domain.education.FacultyDepartment;
import by.kopyshev.university.domain.education.role.Educator;
import by.kopyshev.university.dto.PersonDTO;
import by.kopyshev.university.dto.education.educator.EducatorDTO;
import by.kopyshev.university.dto.education.educator.EducatorPreviewDTO;
import by.kopyshev.university.dto.education.educator.EducatorUpdateDTO;
import by.kopyshev.university.mapper.PersonMapper;
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
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;
    private final LectureHallRepository lectureHallRepository;
    private final FacultyDepartmentRepository facultyDepartmentRepository;

    public EducatorMapper(PersonMapper personMapper, PersonRepository personRepository, LectureHallRepository lectureHallRepository,
                          FacultyDepartmentRepository facultyDepartmentRepository) {
        this.personMapper = personMapper;
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
            destination.setPersonDTO(personMapper.toDTO(source.getPerson()));
            return destination;
        };
        educatorMapper.createTypeMap(Educator.class, EducatorDTO.class)
                .addMappings(mapper -> mapper.skip(EducatorDTO::setLectureHallId))
                .addMappings(mapper -> mapper.skip(EducatorDTO::setFacultyDepartmentId))
                .setPostConverter(toDTOPostConverter);

        Converter<Educator, EducatorPreviewDTO> toPreviewDTOPostConverter = ctx -> {
            Person person = ctx.getSource().getPerson();
            String name = person.getLastName() + " " +
                    person.getFirstName().substring(0, 0) + "." +
                    person.getMiddleName().substring(0, 0) + ".";
            ctx.getDestination().setName(name);
            return ctx.getDestination();
        };
        educatorMapper.createTypeMap(Educator.class, EducatorPreviewDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getLectureHall().getId(), EducatorPreviewDTO::setLectureHallId))
                .addMappings(mapper -> mapper.map(s -> s.getFacultyDepartment().getId(),
                        EducatorPreviewDTO::setFacultyDepartmentId))
                .addMappings(mapper -> mapper.map(s -> s.getPerson().getId(), EducatorPreviewDTO::setPersonId))
                .setPostConverter(toPreviewDTOPostConverter);

        educatorMapper.createTypeMap(EducatorDTO.class, EducatorPreviewDTO.class)
                .addMappings(mapper -> mapper.map(EducatorDTO::getLectureHallId, EducatorPreviewDTO::setLectureHallId))
                .addMappings(mapper -> mapper.map(EducatorDTO::getFacultyDepartmentId, EducatorPreviewDTO::setFacultyDepartmentId))
                .addMappings(mapper -> mapper.map(s -> s.getPersonDTO().getId(), EducatorPreviewDTO::setPersonId))
                .setPostConverter(ctx -> {
                    PersonDTO person = ctx.getSource().getPersonDTO();
                    String name = person.getLastName() + " " +
                            person.getFirstName().substring(0, 0) + "." +
                            person.getMiddleName().substring(0, 0) + ".";
                    ctx.getDestination().setName(name);
                    return ctx.getDestination();
                });
    }

    public Educator toEntity(EducatorUpdateDTO educatorUpdateDTO) {
        return educatorMapper.map(educatorUpdateDTO, Educator.class);
    }

    public EducatorDTO toDTO(Educator educator) {
        return educatorMapper.map(educator, EducatorDTO.class);
    }

    public EducatorPreviewDTO toPreviewDTO(Educator educator) {
        return educatorMapper.map(educator, EducatorPreviewDTO.class);
    }

    public EducatorPreviewDTO toPreviewDTO(EducatorDTO educatorDTO) {
        return educatorMapper.map(educatorDTO, EducatorPreviewDTO.class);
    }

    public List<EducatorDTO> toDTO(List<Educator> educators) {
        return educators.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<EducatorPreviewDTO> toPreviewDTO(List<Educator> educators) {
        return educators.stream().map(this::toPreviewDTO).collect(Collectors.toList());
    }

    public List<EducatorPreviewDTO> dtoToPreviewDTO(List<EducatorDTO> educatorDTOs) {
        return educatorDTOs.stream().map(this::toPreviewDTO).collect(Collectors.toList());
    }
}
