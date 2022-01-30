package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.education.Faculty;
import by.kopyshev.university.domain.education.FacultyDepartment;
import by.kopyshev.university.dto.education.FacultyDepartmentDTO;
import by.kopyshev.university.dto.education.FacultyDepartmentWithDisciplinesDTO;
import by.kopyshev.university.mapper.education.lecture.DisciplineMapper;
import by.kopyshev.university.repository.education.FacultyRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

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
        Converter<FacultyDepartmentDTO, FacultyDepartment> toEntityPostConverter = ctx -> {
            Faculty faculty = facultyRepository.getById(ctx.getSource().getFacultyId());
            ctx.getDestination().setFaculty(faculty);
            return ctx.getDestination();
        };
        facultyDepartmentMapper.createTypeMap(FacultyDepartmentDTO.class, FacultyDepartment.class)
                .addMappings(mapper -> mapper.skip(FacultyDepartment::setFaculty))
                .setPostConverter(toEntityPostConverter);
        
        Converter<FacultyDepartment, FacultyDepartmentDTO> toDTOPostConverter = ctx -> {
            ctx.getDestination().setFacultyId(ctx.getSource().getFaculty().getId());
            return ctx.getDestination();
        };
        facultyDepartmentMapper.createTypeMap(FacultyDepartment.class, FacultyDepartmentDTO.class)
                .addMappings(mapper -> mapper.skip(FacultyDepartmentDTO::setFacultyId))
                .setPostConverter(toDTOPostConverter);

        Converter<FacultyDepartment, FacultyDepartmentWithDisciplinesDTO> toDTOWithDisciplines = ctx -> {
            ctx.getDestination().setDisciplineDTOs(disciplineMapper.toDTO(ctx.getSource().getDisciplines()));
            return ctx.getDestination();
        };
        facultyDepartmentMapper.createTypeMap(FacultyDepartment.class, FacultyDepartmentWithDisciplinesDTO.class)
                .addMappings(mapper -> mapper.skip(FacultyDepartmentWithDisciplinesDTO::setDisciplineDTOs))
                .setPostConverter(toDTOWithDisciplines);
    }

    public FacultyDepartment toEntity(FacultyDepartmentDTO facultyDepartmentDTO) {
        return facultyDepartmentMapper.map(facultyDepartmentDTO, FacultyDepartment.class);
    }

    public FacultyDepartmentDTO toDTO(FacultyDepartment facultyDepartment) {
        return facultyDepartmentMapper.map(facultyDepartment, FacultyDepartmentDTO.class);
    }

    public FacultyDepartmentWithDisciplinesDTO toDTOWithDisciplines(FacultyDepartment facultyDepartment) {
        return facultyDepartmentMapper.map(facultyDepartment, FacultyDepartmentWithDisciplinesDTO.class);
    }

    public List<FacultyDepartmentDTO> toDTO(List<FacultyDepartment> facultyDepartments) {
        return facultyDepartments.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<FacultyDepartmentWithDisciplinesDTO> toDTOWithDisciplines(List<FacultyDepartment> facultyDepartments) {
        return facultyDepartments.stream().map(this::toDTOWithDisciplines).collect(Collectors.toList());
    }
}
