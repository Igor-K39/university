package by.kopyshev.university.mappers.building;

import by.kopyshev.university.domain.building.Campus;
import by.kopyshev.university.dto.building.CampusDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CampusMapper {
    private final ModelMapper campusMapper = new ModelMapper();

    @PostConstruct
    public void setup() {
        campusMapper.createTypeMap(Campus.class, CampusDTO.class);
    }

    public Campus toEntity(CampusDTO campusDTO) {
        return campusMapper.map(campusDTO, Campus.class);
    }

    public CampusDTO toDTO(Campus campus) {
        return campusMapper.map(campus, CampusDTO.class);
    }

    public List<CampusDTO> toDTO(List<Campus> campuses) {
        return campuses.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
