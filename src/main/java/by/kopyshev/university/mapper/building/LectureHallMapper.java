package by.kopyshev.university.mapper.building;

import by.kopyshev.university.domain.building.Campus;
import by.kopyshev.university.domain.building.LectureHall;
import by.kopyshev.university.dto.building.LectureHallDTO;
import by.kopyshev.university.repository.building.CampusRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LectureHallMapper {
    private final ModelMapper lectureHallMapper = new ModelMapper();
    private final CampusRepository campusRepository;

    public LectureHallMapper(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }

    @PostConstruct
    public void setup() {
        Converter<LectureHall, LectureHallDTO> lectureHallDTOPostConverter = ctx -> {
            LectureHallDTO destination = ctx.getDestination();
            destination.setCampusId(ctx.getSource().getCampus().id());
            return destination;
        };
        lectureHallMapper.createTypeMap(LectureHall.class, LectureHallDTO.class)
                .addMappings(mapper -> mapper.skip(LectureHallDTO::setCampusId))
                .setPostConverter(lectureHallDTOPostConverter);

        Converter<LectureHallDTO, LectureHall> lectureHallUpdatePostConverter = ctx -> {
            Campus campus = campusRepository.getById(ctx.getSource().getCampusId());
            LectureHall lectureHall = ctx.getDestination();
            lectureHall.setCampus(campus);
            return lectureHall;
        };
        lectureHallMapper.createTypeMap(LectureHallDTO.class, LectureHall.class)
                .addMappings(mapper -> mapper.skip(LectureHall::setCampus))
                .setPostConverter(lectureHallUpdatePostConverter);
    }

    public LectureHall toEntity(LectureHallDTO lectureHallDTO) {
        return lectureHallMapper.map(lectureHallDTO, LectureHall.class);
    }

    public LectureHallDTO toDTO(LectureHall lectureHall) {
        return lectureHallMapper.map(lectureHall, LectureHallDTO.class);
    }

    public List<LectureHallDTO> toDTO(List<LectureHall> lectureHalls) {
        return lectureHalls.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
