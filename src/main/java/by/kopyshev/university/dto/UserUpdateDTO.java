package by.kopyshev.university.dto;

import by.kopyshev.university.domain.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

public class UserUpdateDTO extends BaseDTO {

    @NotNull
    @Positive
    private Integer personId;

    @NotNull
    @Size(min = 5, max = 50)
    private String username;

    @NotNull
    @Size(min = 8, max = 100)
    private String password;

    private boolean enabled = true;

    private Date registered = new Date();

    private Set<Role> roles = Set.of();

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(Integer id, Integer personId, String username, String password,
                         boolean enabled, Date registered, Set<Role> roles) {
        super(id);
        this.personId = personId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.roles = Set.copyOf(roles);
    }

    public UserUpdateDTO(UserUpdateDTO updateDTO) {
        this(updateDTO.id, updateDTO.personId, updateDTO.username, updateDTO.password, updateDTO.enabled,
                updateDTO.registered, updateDTO.roles);
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
        this.roles = Set.copyOf(roles);
    }
}
