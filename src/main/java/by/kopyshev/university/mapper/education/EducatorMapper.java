package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.Person;
import by.kopyshev.university.domain.building.LectureHall;
import by.kopyshev.university.domain.education.FacultyDepartment;
import by.kopyshev.university.domain.education.role.Educator;
import by.kopyshev.university.dto.education.educator.EducatorDTO;
import by.kopyshev.university.dto.education.educator.EducatorPreviewDTO;
import by.kopyshev.university.dto.education.educator.EducatorUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.PersonMapper;
import by.kopyshev.university.repository.PersonRepository;
import by.kopyshev.university.repository.building.LectureHallRepository;
import by.kopyshev.university.repository.education.FacultyDepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class EducatorMapper {
    private final ModelMapper educatorMapper = new ModelMapper();
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;
    private final LectureHallRepository lectureHallRepository;
    private final FacultyDepartmentRepository departmentRepository;

    public EducatorMapper(PersonMapper personMapper, PersonRepository personRepository, LectureHallRepository lectureHallRepository,
                          FacultyDepartmentRepository departmentRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
        this.lectureHallRepository = lectureHallRepository;
        this.departmentRepository = departmentRepository;
    }

    @PostConstruct
    public void setup() {
        educatorMapper.createTypeMap(EducatorUpdateDTO.class, Educator.class)
                .addMappings(mapper -> mapper.skip(Educator::setPerson))
                .addMappings(mapper -> mapper.skip(Educator::setLectureHall))
                .addMappings(mapper -> mapper.skip(Educator::setFacultyDepartment))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();

                    var personId = source.getPersonId();
                    var person = personRepository.findById(personId).orElseThrow(
                            () -> new NotFoundException(Person.class, "id = " + personId));
                    destination.setPerson(person);

                    var lectureHallId = source.getLectureHallId();
                    var lectureHall = lectureHallRepository.findById(lectureHallId).orElseThrow(
                            () -> new NotFoundException(LectureHall.class, "id = " + lectureHallId));
                    destination.setLectureHall(lectureHall);

                    var departmentId = source.getFacultyDepartmentId();
                    var department = departmentRepository.findById(departmentId)
                            .orElseThrow(() -> new NotFoundException(FacultyDepartment.class, "id = " + departmentId));
                    destination.setFacultyDepartment(department);
                    return destination;
                });

        educatorMapper.createTypeMap(Educator.class, EducatorDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getLectureHall().getId(), EducatorDTO::setLectureHallId))
                .addMappings(mapper -> mapper.map(s -> s.getFacultyDepartment().getId(), EducatorDTO::setFacultyDepartmentId))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();
                    destination.setPerson(personMapper.toDTO(source.getPerson()));
                    return destination;
                });

        educatorMapper.createTypeMap(Educator.class, EducatorPreviewDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getLectureHall().getId(), EducatorPreviewDTO::setLectureHallId))
                .addMappings(mapper -> mapper.map(s -> s.getFacultyDepartment().getId(), EducatorPreviewDTO::setFacultyDepartmentId))
                .addMappings(mapper -> mapper.map(s -> s.getPerson().getId(), EducatorPreviewDTO::setPersonId))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();
                    destination.setName(source.getPerson().getShortName());
                    return destination;
                });

        educatorMapper.createTypeMap(EducatorDTO.class, EducatorPreviewDTO.class)
                .addMappings(mapper -> mapper.map(EducatorDTO::getLectureHallId, EducatorPreviewDTO::setLectureHallId))
                .addMappings(mapper -> mapper.map(EducatorDTO::getFacultyDepartmentId, EducatorPreviewDTO::setFacultyDepartmentId))
                .addMappings(mapper -> mapper.map(s -> s.getPerson().getId(), EducatorPreviewDTO::setPersonId))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();
                    destination.setName(source.getPerson().getShortName());
                    return destination;
                });
    }

    public Educator toEntity(EducatorUpdateDTO educatorUpdateDTO) {
        return isNull(educatorUpdateDTO)
                ? null
                : educatorMapper.map(educatorUpdateDTO, Educator.class);
    }

    public EducatorDTO toDTO(Educator educator) {
        return isNull(educator)
                ? null
                : educatorMapper.map(educator, EducatorDTO.class);
    }

    public EducatorPreviewDTO toPreviewDTO(Educator educator) {
        return isNull(educator)
                ? null
                : educatorMapper.map(educator, EducatorPreviewDTO.class);
    }

    public EducatorPreviewDTO toPreviewDTO(EducatorDTO educatorDTO) {
        return isNull(educatorDTO)
                ? null
                : educatorMapper.map(educatorDTO, EducatorPreviewDTO.class);
    }

    public List<EducatorDTO> toDTO(List<Educator> educators) {
        return isNull(educators)
                ? null
                : educators.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<EducatorPreviewDTO> toPreviewDTO(List<Educator> educators) {
        return isNull(educators)
                ? null
                : educators.stream().map(this::toPreviewDTO).collect(Collectors.toList());
    }

    public List<EducatorPreviewDTO> dtoToPreviewDTO(List<EducatorDTO> educatorDTOs) {
        return isNull(educatorDTOs)
                ? null
                : educatorDTOs.stream().map(this::toPreviewDTO).collect(Collectors.toList());
    }
}
