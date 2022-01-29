package by.kopyshev.university.service;

import by.kopyshev.university.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    PersonDTO create(PersonDTO personDTO);

    PersonDTO get(int id);

    PersonDTO getByPassport(String passport);

    List<PersonDTO> getAll();

    void update(PersonDTO personDTO);

    void delete(int id);
}
