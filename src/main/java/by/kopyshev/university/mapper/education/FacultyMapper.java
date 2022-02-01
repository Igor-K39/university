package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.education.Faculty;
import by.kopyshev.university.dto.education.FacultyDTO;
import by.kopyshev.university.dto.education.FacultyWithDepartmentsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class FacultyMapper {
    private final ModelMapper facultyMapper = new ModelMapper();
    private final FacultyDepartmentMapper departmentMapper;

    public FacultyMapper(FacultyDepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @PostConstruct
    public void setup() {
        facultyMapper.createTypeMap(Faculty.class, FacultyDTO.class);
        facultyMapper.createTypeMap(Faculty.class, FacultyWithDepartmentsDTO.class)
                .addMappings(mapper -> mapper.skip(FacultyWithDepartmentsDTO::setFacultyDepartments))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();
                    var departmentsDTO = departmentMapper.toDTO(source.getFacultyDepartments());
                    destination.setFacultyDepartments(departmentsDTO);
                    return destination;
                });
    }

    public Faculty toEntity(FacultyDTO facultyDTO) {
        return isNull(facultyDTO)
                ? null
                : facultyMapper.map(facultyDTO, Faculty.class);
    }

    public FacultyDTO toDTO(Faculty faculty) {
        return isNull(faculty)
                ? null
                : facultyMapper.map(faculty, FacultyDTO.class);
    }

    public List<FacultyDTO> toDTO(List<Faculty> faculty) {
        return isNull(faculty)
                ? null
                : faculty.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public FacultyWithDepartmentsDTO toDTOWithDepartments(Faculty faculty) {
        return isNull(faculty)
                ? null
                : facultyMapper.map(faculty, FacultyWithDepartmentsDTO.class);
    }

    public List<FacultyWithDepartmentsDTO> toDTOWithDepartments(List<Faculty> faculties) {
        return isNull(faculties)
                ? null
                : faculties.stream().map(this::toDTOWithDepartments).collect(Collectors.toList());
    }
}
