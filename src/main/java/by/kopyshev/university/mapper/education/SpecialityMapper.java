package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.education.Faculty;
import by.kopyshev.university.domain.education.Speciality;
import by.kopyshev.university.dto.education.SpecialityDTO;
import by.kopyshev.university.repository.education.FacultyRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpecialityMapper {
    private final ModelMapper specialityMapper = new ModelMapper();
    private final FacultyRepository facultyRepository;

    public SpecialityMapper(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @PostConstruct
    public void setup() {
        Converter<SpecialityDTO, Speciality> toEntityPostConverter = ctx -> {
            Faculty faculty = facultyRepository.getById(ctx.getSource().getFacultyId());
            ctx.getDestination().setFaculty(faculty);
            return ctx.getDestination();
        };
        specialityMapper.createTypeMap(SpecialityDTO.class, Speciality.class)
                .addMappings(mapper -> mapper.skip(Speciality::setFaculty))
                .setPostConverter(toEntityPostConverter);

        Converter<Speciality, SpecialityDTO> toDTOPostConverter = ctx -> {
            ctx.getDestination().setFacultyId(ctx.getSource().getFaculty().getId());
            return ctx.getDestination();
        };
        specialityMapper.createTypeMap(Speciality.class, SpecialityDTO.class)
                .addMappings(mapper -> mapper.skip(SpecialityDTO::setFacultyId))
                .setPostConverter(toDTOPostConverter);
    }

    public Speciality toEntity(SpecialityDTO specialityDTO) {
        return specialityMapper.map(specialityDTO, Speciality.class);
    }

    public SpecialityDTO toDTO(Speciality speciality) {
        return specialityMapper.map(speciality, SpecialityDTO.class);
    }

    public List<SpecialityDTO> toDTO(List<Speciality> specialities) {
        return specialities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
