package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.education.Faculty;
import by.kopyshev.university.domain.education.Speciality;
import by.kopyshev.university.dto.education.SpecialityDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.repository.education.FacultyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class SpecialityMapper {
    private final ModelMapper specialityMapper = new ModelMapper();
    private final FacultyRepository facultyRepository;

    public SpecialityMapper(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @PostConstruct
    public void setup() {
        specialityMapper.createTypeMap(SpecialityDTO.class, Speciality.class)
                .addMappings(mapper -> mapper.skip(Speciality::setFaculty))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();
                    var facultyId = source.getFacultyId();
                    var faculty = facultyRepository.findById(facultyId).orElseThrow(
                            () -> new NotFoundException(Faculty.class, "id  = " + facultyId));
                    destination.setFaculty(faculty);
                    return destination;
                });

        specialityMapper.createTypeMap(Speciality.class, SpecialityDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getFaculty().getId(), SpecialityDTO::setFacultyId));
    }

    public Speciality toEntity(SpecialityDTO specialityDTO) {
        return isNull(specialityDTO)
                ? null
                : specialityMapper.map(specialityDTO, Speciality.class);
    }

    public SpecialityDTO toDTO(Speciality speciality) {
        return isNull(speciality)
                ? null
                : specialityMapper.map(speciality, SpecialityDTO.class);
    }

    public List<SpecialityDTO> toDTO(List<Speciality> specialities) {
        return isNull(specialities)
                ? null
                : specialities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
