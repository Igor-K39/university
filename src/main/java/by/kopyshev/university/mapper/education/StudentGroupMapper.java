package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.education.Speciality;
import by.kopyshev.university.domain.education.StudentGroup;
import by.kopyshev.university.domain.education.role.Educator;
import by.kopyshev.university.dto.education.group.StudentGroupDTO;
import by.kopyshev.university.dto.education.group.StudentGroupUpdateDTO;
import by.kopyshev.university.dto.education.group.StudentGroupWithStudentsDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.repository.education.EducatorRepository;
import by.kopyshev.university.repository.education.SpecialityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

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
        studentGroupMapper.createTypeMap(StudentGroup.class, StudentGroupDTO.class)
                .addMappings(mapper -> mapper.skip(StudentGroupDTO::setSpeciality))
                .addMappings(mapper -> mapper.skip(StudentGroupDTO::setCurator))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();
                    destination.setSpeciality(specialityMapper.toDTO(source.getSpeciality()));
                    destination.setCurator(educatorMapper.toDTO(source.getCurator()));
                    return destination;
                });


        studentGroupMapper.createTypeMap(StudentGroupUpdateDTO.class, StudentGroup.class)
                .addMappings(mapper -> mapper.skip(StudentGroup::setSpeciality))
                .addMappings(mapper -> mapper.skip(StudentGroup::setCurator))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();

                    var specialityId = source.getSpecialityId();
                    var speciality = specialityRepository.findById(source.getSpecialityId()).orElseThrow(
                            () -> new NotFoundException(Speciality.class, "id = " + specialityId));
                    destination.setSpeciality(speciality);

                    var curatorId = source.getCuratorId();
                    var curator = educatorRepository.findById(source.getCuratorId()).orElseThrow(
                            () -> new NotFoundException(Educator.class, "id = " + curatorId));
                    destination.setCurator(curator);
                    return destination;
                });

        studentGroupMapper.createTypeMap(StudentGroup.class, StudentGroupWithStudentsDTO.class)
                .addMappings(mapper -> mapper.skip(StudentGroupWithStudentsDTO::setStudentDTOs))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();
                    destination.setStudentDTOs(studentMapper.toDTO(source.getStudents()));
                    return destination;
                });
    }

    public StudentGroup toEntity(StudentGroupUpdateDTO studentGroupUpdateDTO) {
        return isNull(studentGroupUpdateDTO)
                ? null
                : studentGroupMapper.map(studentGroupUpdateDTO, StudentGroup.class);
    }

    public StudentGroupDTO toDTO(StudentGroup studentGroup) {
        return isNull(studentGroup)
                ? null
                : studentGroupMapper.map(studentGroup, StudentGroupDTO.class);
    }

    public StudentGroupWithStudentsDTO toDTOWithStudents(StudentGroup studentGroup) {
        return isNull(studentGroup)
                ? null
                : studentGroupMapper.map(studentGroup, StudentGroupWithStudentsDTO.class);
    }

    public List<StudentGroupDTO> toDTO(List<StudentGroup> studentGroups) {
        return isNull(studentGroups)
                ? null
                : studentGroups.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<StudentGroupWithStudentsDTO> toDTOWithStudents(List<StudentGroup> studentGroups) {
        return isNull(studentGroups)
                ? null
                : studentGroups.stream().map(this::toDTOWithStudents).collect(Collectors.toList());
    }
}
