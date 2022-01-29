package by.kopyshev.university.mappers.education;

import by.kopyshev.university.domain.education.Faculty;
import by.kopyshev.university.dto.education.FacultyDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FacultyMapper {
    private final ModelMapper facultyMapper = new ModelMapper();

    @PostConstruct
    public void setup() {
        facultyMapper.createTypeMap(Faculty.class, FacultyDTO.class);
    }

    public Faculty toEntity(FacultyDTO facultyDTO) {
        return facultyMapper.map(facultyDTO, Faculty.class);
    }

    public FacultyDTO toDTO(Faculty faculty) {
        return facultyMapper.map(faculty, FacultyDTO.class);
    }

    public List<FacultyDTO> toDTO(List<Faculty> lectureHalls) {
        return lectureHalls.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
