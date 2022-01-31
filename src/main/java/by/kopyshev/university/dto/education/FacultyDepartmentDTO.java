package by.kopyshev.university.dto.education;

import by.kopyshev.university.dto.NamedDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class FacultyDepartmentDTO extends NamedDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    protected String address;

    @NotBlank
    @Size(min = 5, max = 40)
    protected String email;

    @NotBlank
    @Size(min = 5, max = 20)
    protected String phoneNumber;

    @Size(max = 1200)
    protected String description;

    @NotNull
    protected int facultyId;

    public FacultyDepartmentDTO() {
    }

    public FacultyDepartmentDTO(Integer id, String name, String address, String email, String phoneNumber,
                                String description, int facultyId) {
        super(id, name);
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.facultyId = facultyId;
    }

    public FacultyDepartmentDTO(FacultyDepartmentDTO departmentDTO) {
        this(departmentDTO.id, departmentDTO.name, departmentDTO.address, departmentDTO.email, departmentDTO.phoneNumber,
                departmentDTO.description, departmentDTO.facultyId);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacultyDepartmentDTO)) return false;
        if (!super.equals(o)) return false;
        FacultyDepartmentDTO that = (FacultyDepartmentDTO) o;
        return getFacultyId() == that.getFacultyId()
                && Objects.equals(getAddress(), that.getAddress())
                && Objects.equals(getEmail(), that.getEmail())
                && Objects.equals(getPhoneNumber(), that.getPhoneNumber())
                && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAddress(), getEmail(),
                getPhoneNumber(), getDescription(), getFacultyId());
    }
}
