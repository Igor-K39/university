package by.kopyshev.university.service.building;

import by.kopyshev.university.domain.building.Campus;
import by.kopyshev.university.dto.building.CampusDTO;
import by.kopyshev.university.dto.building.CampusWithHallsDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mappers.building.CampusMapper;
import by.kopyshev.university.repository.building.CampusRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.checkNew;
import static by.kopyshev.university.util.ValidationUtil.checkNotFoundWithId;

@Service
public class CampusServiceImpl implements CampusService {
    private final CampusMapper mapper;
    private final CampusRepository repository;

    public CampusServiceImpl(CampusMapper mapper, CampusRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public CampusDTO create(CampusDTO campusDTO) {
        checkNew(campusDTO);
        Campus saved = repository.save(mapper.toEntity(campusDTO));
        return mapper.toDTO(saved);
    }

    @Override
    public CampusDTO get(int id) {
        Campus campus = repository.findById(id).orElseThrow(() -> new NotFoundException(Campus.class, "id = " + id));
        return mapper.toDTO(campus);
    }

    @Override
    public CampusDTO getByNumber(String number) {
        Campus campus = repository.getByNumber(number)
                .orElseThrow(() -> new NotFoundException(Campus.class, "nuber = " + number));
        return mapper.toDTO(campus);
    }

    @Override
    public CampusWithHallsDTO getWithHalls(int id) {
        Campus campus = repository.getWithHalls(id)
                .orElseThrow(() -> new NotFoundException(CampusWithHallsDTO.class, "id = " + id));
        return mapper.toDTOWithHalls(campus);
    }

    @Override
    public List<CampusDTO> getAll() {
        List<Campus> campuses = repository.getAll(Sort.by(Sort.Direction.ASC, "number")).orElse(List.of());
        return mapper.toDTO(campuses);
    }

    @Override
    public List<CampusWithHallsDTO> getAllWithHalls() {
        List<Campus> campuses = repository.getAllWithHalls().orElse(List.of());
        return mapper.toDTOWithHalls(campuses);
    }

    @Override
    @Transactional
    public void update(CampusDTO campusDTO) {
        int id = campusDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(Campus.class, "id = " + id));
        repository.save(mapper.toEntity(campusDTO));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Campus.class, id);
    }
}
