package by.kopyshev.university.dto.education;

import by.kopyshev.university.dto.NamedDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class FacultyDTO extends NamedDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String deanAddress;

    @NotBlank
    @Size(min = 5, max = 40)
    private String deanEmail;

    @NotBlank
    @Size(min = 5, max = 20)
    private String deanPhoneNumber;

    @Size(max = 1200)
    private String description;

    public FacultyDTO() {
    }

    public FacultyDTO(Integer id, String name, String deanAddress, String deanEmail, String deanPhoneNumber,
                      String description) {
        super(id, name);
        this.deanAddress = deanAddress;
        this.deanEmail = deanEmail;
        this.deanPhoneNumber = deanPhoneNumber;
        this.description = description;
    }

    public FacultyDTO(FacultyDTO facultyDTO) {
        this(facultyDTO.id, facultyDTO.name, facultyDTO.deanAddress, facultyDTO.deanEmail, facultyDTO.deanPhoneNumber,
                facultyDTO.description);
    }

    public String getDeanAddress() {
        return deanAddress;
    }

    public void setDeanAddress(String deanAddress) {
        this.deanAddress = deanAddress;
    }

    public String getDeanEmail() {
        return deanEmail;
    }

    public void setDeanEmail(String deanEmail) {
        this.deanEmail = deanEmail;
    }

    public String getDeanPhoneNumber() {
        return deanPhoneNumber;
    }

    public void setDeanPhoneNumber(String deanPhoneNumber) {
        this.deanPhoneNumber = deanPhoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacultyDTO)) return false;
        if (!super.equals(o)) return false;
        FacultyDTO that = (FacultyDTO) o;
        return Objects.equals(deanAddress, that.deanAddress) && Objects.equals(deanEmail, that.deanEmail)
                && Objects.equals(deanPhoneNumber, that.deanPhoneNumber)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deanAddress, deanEmail, deanPhoneNumber, description);
    }

    @Override
    public String toString() {
        return "FacultyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deanAddress='" + deanAddress + '\'' +
                ", deanEmail='" + deanEmail + '\'' +
                ", deanPhoneNumber='" + deanPhoneNumber + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
