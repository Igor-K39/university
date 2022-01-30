package by.kopyshev.university.service.building;

import by.kopyshev.university.domain.building.LectureHall;
import by.kopyshev.university.dto.building.LectureHallDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.building.LectureHallMapper;
import by.kopyshev.university.repository.building.LectureHallRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.checkNew;
import static by.kopyshev.university.util.ValidationUtil.checkNotFoundWithId;

@Service
public class LectureHallServiceImpl implements LectureHallService {
    private final LectureHallMapper mapper;
    private final LectureHallRepository repository;

    public LectureHallServiceImpl(LectureHallMapper mapper, LectureHallRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public LectureHallDTO create(LectureHallDTO lectureHallDTO) {
        checkNew(lectureHallDTO);
        LectureHall saved = repository.save(mapper.toEntity(lectureHallDTO));
        return mapper.toDTO(saved);
    }

    @Override
    public LectureHallDTO get(int id) {
        LectureHall lectureHall = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(LectureHall.class, "id = " + id));
        return mapper.toDTO(lectureHall);
    }

    @Override
    public LectureHallDTO getByNumber(String campusNumber, String number) {
        LectureHall lectureHall = repository.getByNumber(campusNumber, number)
                .orElseThrow(() -> new NotFoundException(LectureHall.class, "number = " + number));
        return mapper.toDTO(lectureHall);
    }

    @Override
    public List<LectureHallDTO> getAll() {
        List<LectureHall> lectureHalls = repository.getAll(Sort.by(Sort.Direction.ASC, "number")).orElse(List.of());
        return mapper.toDTO(lectureHalls);
    }

    @Override
    @Transactional
    public void update(LectureHallDTO lectureHallDTO) {
        int id = lectureHallDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(LectureHall.class, "id = " + id));
        repository.save(mapper.toEntity(lectureHallDTO));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, LectureHall.class, id);
    }
}
