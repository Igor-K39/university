package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.education.Faculty;
import by.kopyshev.university.domain.education.FacultyDepartment;
import by.kopyshev.university.dto.education.FacultyDepartmentDTO;
import by.kopyshev.university.dto.education.FacultyDepartmentWithDisciplinesDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.repository.education.FacultyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class FacultyDepartmentMapper {
    private final ModelMapper facultyDepartmentMapper = new ModelMapper();
    private final DisciplineMapper disciplineMapper;
    private final FacultyRepository facultyRepository;

    public FacultyDepartmentMapper(DisciplineMapper disciplineMapper, FacultyRepository facultyRepository) {
        this.disciplineMapper = disciplineMapper;
        this.facultyRepository = facultyRepository;
    }

    @PostConstruct
    public void setup() {
        facultyDepartmentMapper.createTypeMap(FacultyDepartmentDTO.class, FacultyDepartment.class)
                .addMappings(mapper -> mapper.skip(FacultyDepartment::setFaculty))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();
                    var facultyId = source.getFacultyId();
                    var faculty = facultyRepository.findById(facultyId).orElseThrow(
                            () -> new NotFoundException(Faculty.class, "id = " + facultyId));
                    destination.setFaculty(faculty);
                    return destination;
                });

        facultyDepartmentMapper.createTypeMap(FacultyDepartment.class, FacultyDepartmentDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getFaculty().getId(), FacultyDepartmentDTO::setFacultyId));

        facultyDepartmentMapper.createTypeMap(FacultyDepartment.class, FacultyDepartmentWithDisciplinesDTO.class)
                .addMappings(mapper -> mapper.skip(FacultyDepartmentWithDisciplinesDTO::setDisciplineDTOs))
                .setPostConverter(ctx -> {
                    var destination = ctx.getDestination();
                    var source = ctx.getSource();
                    var disciplinesDTO = disciplineMapper.toDTO(source.getDisciplines());
                    destination.setDisciplineDTOs(disciplinesDTO);
                    return destination;
                });
    }

    public FacultyDepartment toEntity(FacultyDepartmentDTO facultyDepartmentDTO) {
        return isNull(facultyDepartmentDTO)
                ? null
                : facultyDepartmentMapper.map(facultyDepartmentDTO, FacultyDepartment.class);
    }

    public FacultyDepartmentDTO toDTO(FacultyDepartment facultyDepartment) {
        return isNull(facultyDepartment)
                ? null
                : facultyDepartmentMapper.map(facultyDepartment, FacultyDepartmentDTO.class);
    }

    public FacultyDepartmentWithDisciplinesDTO toDTOWithDisciplines(FacultyDepartment facultyDepartment) {
        return isNull(facultyDepartment)
                ? null
                : facultyDepartmentMapper.map(facultyDepartment, FacultyDepartmentWithDisciplinesDTO.class);
    }

    public List<FacultyDepartmentDTO> toDTO(List<FacultyDepartment> facultyDepartments) {
        return isNull(facultyDepartments)
                ? null
                : facultyDepartments.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<FacultyDepartmentWithDisciplinesDTO> toDTOWithDisciplines(List<FacultyDepartment> facultyDepartments) {
        return isNull(facultyDepartments)
                ? null
                : facultyDepartments.stream().map(this::toDTOWithDisciplines).collect(Collectors.toList());
    }
}
