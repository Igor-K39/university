package by.kopyshev.university.mapper.building;

import by.kopyshev.university.domain.building.Campus;
import by.kopyshev.university.domain.building.LectureHall;
import by.kopyshev.university.dto.building.LectureHallDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.repository.building.CampusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class LectureHallMapper {
    private final ModelMapper lectureHallMapper = new ModelMapper();
    private final CampusRepository campusRepository;

    public LectureHallMapper(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    @PostConstruct
    public void setup() {
        lectureHallMapper.createTypeMap(LectureHall.class, LectureHallDTO.class)
                .addMappings(mapper -> mapper.skip(LectureHallDTO::setCampusId))
                .setPostConverter(ctx -> {
                    var destination = ctx.getDestination();
                    var id = ctx.getSource().getCampus().getId();
                    destination.setCampusId(id);
                    return destination;
                });

        lectureHallMapper.createTypeMap(LectureHallDTO.class, LectureHall.class)
                .addMappings(mapper -> mapper.skip(LectureHall::setCampus))
                .setPostConverter(ctx -> {
                    var campus = campusRepository.findById(ctx.getSource().getCampusId()).orElseThrow(
                            () -> new NotFoundException(Campus.class, "id = " + ctx.getSource().getCampusId()));
                    var destination = ctx.getDestination();
                    destination.setCampus(campus);
                    return destination;
                });
    }

    public LectureHall toEntity(LectureHallDTO lectureHallDTO) {
        return isNull(lectureHallDTO)
                ? null
                : lectureHallMapper.map(lectureHallDTO, LectureHall.class);
    }

    public LectureHallDTO toDTO(LectureHall lectureHall) {
        return isNull(lectureHall)
                ? null
                : lectureHallMapper.map(lectureHall, LectureHallDTO.class);
    }

    public List<LectureHallDTO> toDTO(List<LectureHall> lectureHalls) {
        return isNull(lectureHalls)
                ? null
                : lectureHalls.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
