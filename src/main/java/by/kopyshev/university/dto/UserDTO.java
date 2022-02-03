package by.kopyshev.university.dto;

import by.kopyshev.university.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

import static java.util.Objects.isNull;

public class UserDTO extends BaseDTO implements UserDetails {

    @NotNull
    private PersonDTO personDTO;

    @NotNull
    @Size(min = 5, max = 50)
    private String username;

    @NotNull
    @Size(min = 8, max = 100)
    private String password;

    private boolean enabled = true;

    private Date registered = new Date();

    private Set<Role> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Integer id, PersonDTO personDTO, String username, String password, boolean enabled, Date registered,
                   Set<Role> roles) {
        super(id);
        this.personDTO = personDTO;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.roles = isNull(roles) ? this.roles : Set.copyOf(roles);
    }

    public UserDTO(UserDTO userDTO) {
        this(userDTO.id, userDTO.personDTO, userDTO.username, userDTO.password, userDTO.enabled, userDTO.registered,
                userDTO.roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = isNull(roles) ? this.roles : Set.copyOf(roles);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
