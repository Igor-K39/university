package by.kopyshev.university.mappers.building;

import by.kopyshev.university.domain.building.Campus;
import by.kopyshev.university.dto.building.CampusDTO;
import by.kopyshev.university.dto.building.CampusWithHallsDTO;
import by.kopyshev.university.dto.building.LectureHallDTO;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampusMapper {
    private final ModelMapper campusMapper = new ModelMapper();
    private final LectureHallMapper lectureHallMapper;

    public CampusMapper(LectureHallMapper lectureHallMapper) {
        this.lectureHallMapper = lectureHallMapper;
    }

    @PostConstruct
    public void setup() {
        campusMapper.createTypeMap(Campus.class, CampusDTO.class);

        Converter<Campus, CampusWithHallsDTO> lectureHallDTOPostConverter = ctx -> {
            List<LectureHallDTO> lectureHallDTOs = lectureHallMapper.toDTO(ctx.getSource().getLectureHalls());
            ctx.getDestination().setLectureHallsDTO(lectureHallDTOs);
            return ctx.getDestination();
        };
        campusMapper.createTypeMap(Campus.class, CampusWithHallsDTO.class)
                .setPostConverter(lectureHallDTOPostConverter);
    }

    public Campus toEntity(CampusDTO campusDTO) {
        return campusMapper.map(campusDTO, Campus.class);
    }

    public CampusDTO toDTO(Campus campus) {
        return campusMapper.map(campus, CampusDTO.class);
    }

    public CampusWithHallsDTO toDTOWithHalls(Campus campus) {
        CampusWithHallsDTO campusWithHallsDTO = campusMapper.map(campus, CampusWithHallsDTO.class);
        System.out.println(campusWithHallsDTO);
        return campusWithHallsDTO;
    }

    public List<CampusDTO> toDTO(List<Campus> campuses) {
        return campuses.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<CampusWithHallsDTO> toDTOWithHalls(List<Campus> campuses) {
        return campuses.stream().map(this::toDTOWithHalls).collect(Collectors.toList());
    }
}
