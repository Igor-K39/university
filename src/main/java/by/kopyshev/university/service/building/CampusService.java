package by.kopyshev.university.service.building;

import by.kopyshev.university.dto.building.CampusDTO;
import by.kopyshev.university.dto.building.CampusWithHallsDTO;

import java.util.List;

public interface CampusService {

    CampusDTO create(CampusDTO campusDTO);

    CampusDTO get(int id);

    CampusDTO getByNumber(String number);

    CampusWithHallsDTO getWithHalls(int id);

    List<CampusDTO> getAll();

    List<CampusWithHallsDTO> getAllWithHalls();

    void update(CampusDTO campusDTO);

    void delete(int id);
}
