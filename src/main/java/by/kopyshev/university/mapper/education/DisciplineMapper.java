package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.education.FacultyDepartment;
import by.kopyshev.university.domain.education.lecture.Discipline;
import by.kopyshev.university.dto.education.lecture.DisciplineDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.repository.education.FacultyDepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class DisciplineMapper {
    private final ModelMapper disciplineMapper = new ModelMapper();
    private final FacultyDepartmentRepository departmentRepository;

    public DisciplineMapper(FacultyDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @PostConstruct
    public void setup() {
        disciplineMapper.createTypeMap(DisciplineDTO.class, Discipline.class)
                .addMappings(mapper -> mapper.skip(Discipline::setFacultyDepartment))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var departmentId = source.getFacultyDepartmentId();
                    var department = departmentRepository.findById(departmentId)
                            .orElseThrow(() -> new NotFoundException(FacultyDepartment.class, "id = " + departmentId));
                    ctx.getDestination().setFacultyDepartment(department);
                    return ctx.getDestination();
                });

        disciplineMapper.createTypeMap(Discipline.class, DisciplineDTO.class)
                .addMappings(mapper -> mapper.skip(DisciplineDTO::setFacultyDepartmentId))
                .setPostConverter(ctx -> {
                    ctx.getDestination().setFacultyDepartmentId(ctx.getSource().getFacultyDepartment().getId());
                    return ctx.getDestination();
                });
    }

    public Discipline toEntity(DisciplineDTO disciplineDTO) {
        return isNull(disciplineDTO)
                ? null
                : disciplineMapper.map(disciplineDTO, Discipline.class);
    }

    public DisciplineDTO toDTO(Discipline discipline) {
        return isNull(discipline)
                ? null
                : disciplineMapper.map(discipline, DisciplineDTO.class);
    }

    public List<DisciplineDTO> toDTO(List<Discipline> disciplines) {
        return isNull(disciplines)
                ? null
                : disciplines.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
