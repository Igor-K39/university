package by.kopyshev.university.service.building;

import by.kopyshev.university.dto.building.CampusDTO;

import java.util.List;

public interface CampusService {

    CampusDTO create(CampusDTO campusDTO);

    CampusDTO get(int id);

    CampusDTO getByNumber(String number);

    List<CampusDTO> getAll();

    void update(CampusDTO campusDTO);

    void delete(int id);
}
