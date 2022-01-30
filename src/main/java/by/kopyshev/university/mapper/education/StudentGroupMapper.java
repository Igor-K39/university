package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.education.StudentGroup;
import by.kopyshev.university.dto.education.group.StudentGroupDTO;
import by.kopyshev.university.dto.education.group.StudentGroupUpdateDTO;
import by.kopyshev.university.dto.education.group.StudentGroupWithStudentsDTO;
import by.kopyshev.university.mapper.education.role.EducatorMapper;
import by.kopyshev.university.mapper.education.role.StudentMapper;
import by.kopyshev.university.repository.education.SpecialityRepository;
import by.kopyshev.university.repository.education.role.EducatorRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentGroupMapper {
    private final ModelMapper studentGroupMapper = new ModelMapper();
    private final SpecialityMapper specialityMapper;
    private final EducatorMapper educatorMapper;
    private final StudentMapper studentMapper;
    private final SpecialityRepository specialityRepository;
    private final EducatorRepository educatorRepository;

    public StudentGroupMapper(SpecialityMapper specialityMapper, SpecialityRepository specialityRepository,
                              EducatorRepository educatorRepository,
                              EducatorMapper educatorMapper, StudentMapper studentMapper) {
        this.specialityMapper = specialityMapper;
        this.specialityRepository = specialityRepository;
        this.educatorRepository = educatorRepository;
        this.educatorMapper = educatorMapper;
        this.studentMapper = studentMapper;
    }

    @PostConstruct
    public void setup() {
        Converter<StudentGroup, StudentGroupDTO> toDTOPostConverter = ctx -> {
            StudentGroup source = ctx.getSource();
            StudentGroupDTO destination = ctx.getDestination();
            destination.setSpecialityDTO(specialityMapper.toDTO(source.getSpeciality()));
            destination.setCurator(educatorMapper.toDTO(source.getCurator()));
            return destination;
        };
        studentGroupMapper.createTypeMap(StudentGroup.class, StudentGroupDTO.class)
                .addMappings(mapper -> mapper.skip(StudentGroupDTO::setSpecialityDTO))
                .addMappings(mapper -> mapper.skip(StudentGroupDTO::setCurator))
                .setPostConverter(toDTOPostConverter);

        Converter<StudentGroupUpdateDTO, StudentGroup> toEntityPostConverter = ctx -> {
            StudentGroupUpdateDTO source = ctx.getSource();
            StudentGroup destination = ctx.getDestination();
            destination.setSpeciality(specialityRepository.getById(source.getSpecialityId()));
            destination.setCurator(educatorRepository.getById(source.getCuratorId()));
            return destination;
        };
        studentGroupMapper.createTypeMap(StudentGroupUpdateDTO.class, StudentGroup.class)
                .addMappings(mapper -> mapper.skip(StudentGroup::setSpeciality))
                .addMappings(mapper -> mapper.skip(StudentGroup::setCurator))
                .setPostConverter(toEntityPostConverter);

        Converter<StudentGroup, StudentGroupWithStudentsDTO> toStudentGroupWithStudentsPostConverter = ctx -> {
            StudentGroup source = ctx.getSource();
            StudentGroupWithStudentsDTO destination = ctx.getDestination();
            destination.setStudentDTOs(studentMapper.toDTO(source.getStudents()));
            return destination;
        };
        studentGroupMapper.createTypeMap(StudentGroup.class, StudentGroupWithStudentsDTO.class)
                .addMappings(mapper -> mapper.skip(StudentGroupWithStudentsDTO::setStudentDTOs))
                .setPostConverter(toStudentGroupWithStudentsPostConverter);
    }

    public StudentGroup toEntity(StudentGroupUpdateDTO facultyDTO) {
        return studentGroupMapper.map(facultyDTO, StudentGroup.class);
    }

    public StudentGroupDTO toDTO(StudentGroup studentGroup) {
        return studentGroupMapper.map(studentGroup, StudentGroupDTO.class);
    }

    public StudentGroupWithStudentsDTO toDTOWithStudents(StudentGroup studentGroup) {
        return studentGroupMapper.map(studentGroup, StudentGroupWithStudentsDTO.class);
    }

    public List<StudentGroupDTO> toDTO(List<StudentGroup> studentGroups) {
        return studentGroups.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<StudentGroupWithStudentsDTO> toDTOWithStudents(List<StudentGroup> studentGroups) {
        return studentGroups.stream().map(this::toDTOWithStudents).collect(Collectors.toList());
    }
}
