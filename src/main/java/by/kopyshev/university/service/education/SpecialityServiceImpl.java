package by.kopyshev.university.service.education;

import by.kopyshev.university.domain.education.Speciality;
import by.kopyshev.university.dto.education.SpecialityDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.education.SpecialityMapper;
import by.kopyshev.university.repository.education.SpecialityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.checkNew;
import static by.kopyshev.university.util.ValidationUtil.checkNotFoundWithId;

@Service
public class SpecialityServiceImpl implements SpecialityService {
    private final SpecialityMapper mapper;
    private final SpecialityRepository repository;

    public SpecialityServiceImpl(SpecialityMapper mapper, SpecialityRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public SpecialityDTO create(SpecialityDTO facultyDTO) {
        checkNew(facultyDTO);
        Speciality saved = repository.save(mapper.toEntity(facultyDTO));
        return mapper.toDTO(saved);
    }

    @Override
    public SpecialityDTO get(int id) {
        Speciality faculty = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Speciality.class, "id = " + id));
        return mapper.toDTO(faculty);
    }

    @Override
    public List<SpecialityDTO> getAll() {
        List<Speciality> faculties = repository.getAll(Sort.by(Sort.Direction.ASC, "name")).orElse(List.of());
        return mapper.toDTO(faculties);
    }

    @Override
    @Transactional
    public void update(SpecialityDTO facultyDTO) {
        int id = facultyDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(Speciality.class, "id = " + id));
        repository.save(mapper.toEntity(facultyDTO));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Speciality.class, id);
    }
}
