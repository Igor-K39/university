package by.kopyshev.university.testdata;

import by.kopyshev.university.MatcherFactory;
import by.kopyshev.university.domain.Role;
import by.kopyshev.university.dto.UserDTO;
import by.kopyshev.university.dto.UserUpdateDTO;

import java.util.Date;
import java.util.Set;

import static by.kopyshev.university.testdata.PersonConstants.*;

public class UserConstants {
    public static final MatcherFactory.Matcher<UserDTO> USER_DTO_MATCHER
            = MatcherFactory.usingIgnoreFieldComparator(UserDTO.class, "password", "registered");

    public static int STUDENT_ID = 100_054;
    public static int DISPATCHER_ID = 100_055;
    public static int ADMIN_ID = 100_056;
    public static String STUDENT_USERNAME = "student";
    public static String DISPATCHER_USERNAME = "dispatcher";
    public static String ADMIN_USERNAME = "admin";
    public static String STUDENT_PASSWORD = "$2a$10$Sz/ioVIcYZExKdrK/jWI.OlF2BY2Xqo4Ekr482IGciLPgZCZuxY0.";
    public static String DISPATCHER_PASSWORD = "$2a$10$.w5FgtGrRZ1je9eQpgCe8OPNTNe1cL1X//u.rwQ5YWQOaCcHV3RWi";
    public static String ADMIN_PASSWORD = "$2a$10$AFacJmhrZtuei1S/UJNwmOBASNbeVpNLR/taEzhSqD..x/LDYwllq";

    public static UserDTO studentDTO = new UserDTO(STUDENT_ID, studentPerson1, STUDENT_USERNAME, STUDENT_PASSWORD,
            true, new Date(), Set.of(Role.STUDENT));

    public static UserDTO dispatcherDTO = new UserDTO(DISPATCHER_ID, educatorPerson1, DISPATCHER_USERNAME,
            DISPATCHER_PASSWORD, true, new Date(), Set.of(Role.DISPATCHER));

    public static UserDTO adminDTO = new UserDTO(ADMIN_ID, educatorPerson2, ADMIN_USERNAME, ADMIN_PASSWORD,
            true, new Date(), Set.of(Role.ADMIN));

    public static UserUpdateDTO getNew() {
        return new UserUpdateDTO(null, studentPerson5.getId(), "newLogin", "newPassword",
                true, new Date(), Set.of(Role.STUDENT));
    }

    public static UserUpdateDTO getUpdated(UserDTO userDTO) {
        UserUpdateDTO updated = new UserUpdateDTO(userDTO.id(), userDTO.getPersonDTO().getId(), userDTO.getUsername(),
                userDTO.getPassword(), userDTO.isEnabled(), userDTO.getRegistered(), userDTO.getRoles());
        updated.setUsername("updatedUsername");
        updated.setPassword("newStudentPassword");
        updated.setEnabled(false);
        updated.setRoles(Set.of(Role.STUDENT, Role.DISPATCHER, Role.ADMIN));
        return updated;
    }
}
