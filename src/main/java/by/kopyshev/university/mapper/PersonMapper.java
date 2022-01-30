package by.kopyshev.university.mapper;

import by.kopyshev.university.domain.Person;
import by.kopyshev.university.dto.PersonDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonMapper {
    private final ModelMapper mapper = new ModelMapper();

    @PostConstruct
    public void setup() {
        mapper.createTypeMap(Person.class, PersonDTO.class);
    }

    public Person toEntity(PersonDTO personDTO) {
        return mapper.map(personDTO, Person.class);
    }

    public PersonDTO toDTO(Person person) {
        return mapper.map(person, PersonDTO.class);
    }

    public List<PersonDTO> toDTO(List<Person> personList) {
        return personList.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
