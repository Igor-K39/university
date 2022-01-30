package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.education.Faculty;
import by.kopyshev.university.dto.education.FacultyDTO;
import by.kopyshev.university.dto.education.FacultyDepartmentDTO;
import by.kopyshev.university.dto.education.FacultyWithDepartmentsDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FacultyMapper {
    private final ModelMapper facultyMapper = new ModelMapper();
    private final FacultyDepartmentMapper facultyDepartmentMapper;

    public FacultyMapper(FacultyDepartmentMapper facultyDepartmentMapper) {
        this.facultyDepartmentMapper = facultyDepartmentMapper;
    }

    @PostConstruct
    public void setup() {
        facultyMapper.createTypeMap(Faculty.class, FacultyDTO.class);

        Converter<Faculty, FacultyWithDepartmentsDTO> toFacultyWithDepartmentsDTO = ctx -> {
            List<FacultyDepartmentDTO> departmentDTOs =
                    facultyDepartmentMapper.toDTO(ctx.getSource().getFacultyDepartments());
            FacultyWithDepartmentsDTO destination = ctx.getDestination();
            destination.setFacultyDepartments(departmentDTOs);
            return destination;
        };
        facultyMapper.createTypeMap(Faculty.class, FacultyWithDepartmentsDTO.class)
                .addMappings(mapper -> mapper.skip(FacultyWithDepartmentsDTO::setFacultyDepartments))
                .setPostConverter(toFacultyWithDepartmentsDTO);
    }

    public Faculty toEntity(FacultyDTO facultyDTO) {
        return facultyMapper.map(facultyDTO, Faculty.class);
    }

    public FacultyDTO toDTO(Faculty faculty) {
        return facultyMapper.map(faculty, FacultyDTO.class);
    }

    public List<FacultyDTO> toDTO(List<Faculty> faculty) {
        return faculty.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public FacultyWithDepartmentsDTO toDTOWithDepartments(Faculty faculty) {
        return facultyMapper.map(faculty, FacultyWithDepartmentsDTO.class);
    }

    public List<FacultyWithDepartmentsDTO> toDTOWithDepartments(List<Faculty> faculties) {
        return faculties.stream().map(this::toDTOWithDepartments).collect(Collectors.toList());
    }
}
