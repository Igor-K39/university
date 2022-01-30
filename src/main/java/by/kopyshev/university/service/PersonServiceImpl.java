package by.kopyshev.university.service;

import by.kopyshev.university.domain.Person;
import by.kopyshev.university.dto.PersonDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.mapper.PersonMapper;
import by.kopyshev.university.repository.PersonRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.kopyshev.university.util.ValidationUtil.checkNew;
import static by.kopyshev.university.util.ValidationUtil.checkNotFoundWithId;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonMapper mapper;
    private final PersonRepository repository;

    public PersonServiceImpl(PersonMapper mapper, PersonRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public PersonDTO create(PersonDTO personDTO) {
        checkNew(personDTO);
        Person saved = repository.save(mapper.toEntity(personDTO));
        return mapper.toDTO(saved);
    }

    @Override
    public PersonDTO get(int id) {
        Person campus = repository.findById(id).orElseThrow(() -> new NotFoundException(Person.class, "id = " + id));
        return mapper.toDTO(campus);
    }

    @Override
    public PersonDTO getByPassport(String passport) {
        Person person = repository.getByPassport(passport)
                .orElseThrow(() -> new NotFoundException(Person.class, "passport = " + passport));
        return mapper.toDTO(person);
    }

    @Override
    public List<PersonDTO> getAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "lastName", "firstName", "middleName");
        List<Person> personList = repository.getAll(sort).orElse(List.of());
        return mapper.toDTO(personList);
    }

    @Override
    @Transactional
    public void update(PersonDTO personDTO) {
        int id = personDTO.id();
        repository.findById(id).orElseThrow(() -> new NotFoundException(Person.class, "id = " + id));
        repository.save(mapper.toEntity(personDTO));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Person.class, id);
    }
}
