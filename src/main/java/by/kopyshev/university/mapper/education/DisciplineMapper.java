package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.education.FacultyDepartment;
import by.kopyshev.university.domain.education.lecture.Discipline;
import by.kopyshev.university.dto.education.DisciplineDTO;
import by.kopyshev.university.repository.education.FacultyDepartmentRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DisciplineMapper {
    private final ModelMapper facultyDepartmentMapper = new ModelMapper();
    private final FacultyDepartmentRepository facultyDepartmentRepository;

    public DisciplineMapper(FacultyDepartmentRepository facultyDepartmentRepository) {
        this.facultyDepartmentRepository = facultyDepartmentRepository;
    }

    @PostConstruct
    public void setup() {
        Converter<DisciplineDTO, Discipline> toEntityPostConverter = ctx -> {
            FacultyDepartment facultyDepartment =
                    facultyDepartmentRepository.getById(ctx.getSource().getFacultyDepartmentId());
            ctx.getDestination().setFacultyDepartment(facultyDepartment);
            return ctx.getDestination();
        };
        facultyDepartmentMapper.createTypeMap(DisciplineDTO.class, Discipline.class)
                .addMappings(mapper -> mapper.skip(Discipline::setFacultyDepartment))
                .setPostConverter(toEntityPostConverter);

        Converter<Discipline, DisciplineDTO> toDTOPostConverter = ctx -> {
            ctx.getDestination().setFacultyDepartmentId(ctx.getSource().getFacultyDepartment().getId());
            return ctx.getDestination();
        };
        facultyDepartmentMapper.createTypeMap(Discipline.class, DisciplineDTO.class)
                .addMappings(mapper -> mapper.skip(DisciplineDTO::setFacultyDepartmentId))
                .setPostConverter(toDTOPostConverter);
    }

    public Discipline toEntity(DisciplineDTO facultyDepartmentDTO) {
        return facultyDepartmentMapper.map(facultyDepartmentDTO, Discipline.class);
    }

    public DisciplineDTO toDTO(Discipline facultyDepartment) {
        return facultyDepartmentMapper.map(facultyDepartment, DisciplineDTO.class);
    }

    public List<DisciplineDTO> toDTO(List<Discipline> facultyDepartments) {
        return facultyDepartments.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
