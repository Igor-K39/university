package by.kopyshev.university.service.education;

import by.kopyshev.university.domain.education.lecture.Lecture;
import by.kopyshev.university.dto.education.lecture.LectureDTO;
import by.kopyshev.university.dto.education.lecture.LectureUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.education.LectureMapper;
import by.kopyshev.university.repository.education.LectureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static by.kopyshev.university.util.DateTimeUtil.getMaxIfNull;
import static by.kopyshev.university.util.DateTimeUtil.getMinIfNull;
import static by.kopyshev.university.util.ValidationUtil.checkNew;
import static by.kopyshev.university.util.ValidationUtil.checkNotFoundWithId;
import static java.util.Objects.isNull;

@Service
public class LectureServiceImpl implements LectureService {
    private final LectureMapper mapper;
    private final LectureRepository repository;

    public LectureServiceImpl(LectureMapper mapper, LectureRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public LectureDTO create(LectureUpdateDTO lectureUpdateDTO) {
        checkNew(lectureUpdateDTO);
        Lecture saved = repository.save(mapper.toEntity(lectureUpdateDTO));
        return mapper.toDTO(saved);
    }

    @Override
    public LectureDTO get(int id) {
        return mapper.toDTO(getById(id));
    }

    @Override
    public List<LectureDTO> getAll(Integer lectureGroupId, LocalDate start, LocalDate end) {
        return mapper.toDTO(getAllByGroup(lectureGroupId, start, end));
    }

    @Override
    @Transactional
    public void update(LectureUpdateDTO lectureUpdateDTO) {
        int id = lectureUpdateDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(Lecture.class, "id = " + id));
        repository.save(mapper.toEntity(lectureUpdateDTO));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Lecture.class, id);
    }

    private Lecture getById(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(Lecture.class, "id = " + id));
    }

    private List<Lecture> getAllByGroup(Integer lectureGroupId, LocalDate start, LocalDate end) {
        LocalDate startDate = getMinIfNull(start);
        LocalDate endDate = getMaxIfNull(end);

        return isNull(lectureGroupId)
                ? repository.getAllByDate(startDate, endDate).orElse(List.of())
                : repository.getAllByGroupAndDate(lectureGroupId, startDate, endDate).orElse(List.of());
    }
}
