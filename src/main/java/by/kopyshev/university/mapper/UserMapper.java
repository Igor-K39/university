package by.kopyshev.university.mapper;

import by.kopyshev.university.domain.Person;
import by.kopyshev.university.domain.User;
import by.kopyshev.university.dto.UserDTO;
import by.kopyshev.university.dto.UserUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class UserMapper {
    private final ModelMapper userMapper = new ModelMapper();
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    public UserMapper(PersonMapper personMapper, PersonRepository personRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

    @PostConstruct
    public void setup() {
        userMapper.createTypeMap(UserUpdateDTO.class, User.class)
                .addMappings(mapper -> mapper.skip(User::setPerson))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();
                    var personId = source.getPersonId();
                    var person = personRepository.findById(personId).orElseThrow(
                            () -> new NotFoundException(Person.class, "id = " + personId));
                    destination.setPerson(person);
                    return destination;
                });

        userMapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(mapper -> mapper.skip(UserDTO::setPersonDTO))
                .setPostConverter(ctx -> {
                    UserDTO destination = ctx.getDestination();
                    destination.setPersonDTO(personMapper.toDTO(ctx.getSource().getPerson()));
                    return destination;
                });
    }

    public User toEntity(UserUpdateDTO userUpdateDTO) {
        return isNull(userUpdateDTO)
                ? null
                : userMapper.map(userUpdateDTO, User.class);
    }

    public UserDTO toDTO(User user) {
        return isNull(user)
                ? null
                : userMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> toDTO(List<User> users) {
        return isNull(users)
                ? null
                : users.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
