package by.kopyshev.university.mappers.education;

import by.kopyshev.university.domain.education.StudentGroup;
import by.kopyshev.university.dto.education.StudentGroupDTO;
import by.kopyshev.university.dto.education.StudentGroupUpdateDTO;
import by.kopyshev.university.mappers.education.role.EducatorMapper;
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
    private final ModelMapper facultyMapper = new ModelMapper();
    private final SpecialityMapper specialityMapper;
    private final EducatorMapper educatorMapper;
    private final SpecialityRepository specialityRepository;
    private final EducatorRepository educatorRepository;

    public StudentGroupMapper(SpecialityMapper specialityMapper, SpecialityRepository specialityRepository,
                              EducatorMapper educatorMapper, EducatorRepository educatorRepository) {
        this.specialityMapper = specialityMapper;
        this.specialityRepository = specialityRepository;
        this.educatorMapper = educatorMapper;
        this.educatorRepository = educatorRepository;
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
        facultyMapper.createTypeMap(StudentGroup.class, StudentGroupDTO.class)
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
        facultyMapper.createTypeMap(StudentGroupUpdateDTO.class, StudentGroup.class)
                .addMappings(mapper -> mapper.skip(StudentGroup::setSpeciality))
                .addMappings(mapper -> mapper.skip(StudentGroup::setCurator))
                .setPostConverter(toEntityPostConverter);
    }

    public StudentGroup toEntity(StudentGroupUpdateDTO facultyDTO) {
        return facultyMapper.map(facultyDTO, StudentGroup.class);
    }

    public StudentGroupDTO toDTO(StudentGroup faculty) {
        return facultyMapper.map(faculty, StudentGroupDTO.class);
    }

    public List<StudentGroupDTO> toDTO(List<StudentGroup> lectureHalls) {
        return lectureHalls.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
