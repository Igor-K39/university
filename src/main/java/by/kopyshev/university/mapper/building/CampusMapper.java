package by.kopyshev.university.mapper.building;

import by.kopyshev.university.domain.building.Campus;
import by.kopyshev.university.dto.building.CampusDTO;
import by.kopyshev.university.dto.building.CampusWithHallsDTO;
import by.kopyshev.university.dto.building.LectureHallDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

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
        campusMapper.createTypeMap(Campus.class, CampusWithHallsDTO.class)
                .setPostConverter(ctx -> {
                    List<LectureHallDTO> lectureHallDTOs = lectureHallMapper.toDTO(ctx.getSource().getLectureHalls());
                    ctx.getDestination().setLectureHallsDTO(lectureHallDTOs);
                    return ctx.getDestination();
                });
    }

    public Campus toEntity(CampusDTO campusDTO) {
        return isNull(campusDTO)
                ? null
                : campusMapper.map(campusDTO, Campus.class);
    }

    public CampusDTO toDTO(Campus campus) {
        return isNull(campus)
                ? null
                : campusMapper.map(campus, CampusDTO.class);
    }

    public CampusWithHallsDTO toDTOWithHalls(Campus campus) {
        return isNull(campus)
                ? null
                : campusMapper.map(campus, CampusWithHallsDTO.class);
    }

    public List<CampusDTO> toDTO(List<Campus> campuses) {
        return isNull(campuses)
                ? null
                : campuses.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<CampusWithHallsDTO> toDTOWithHalls(List<Campus> campuses) {
        return isNull(campuses)
                ? null
                : campuses.stream().map(this::toDTOWithHalls).collect(Collectors.toList());
    }
}
