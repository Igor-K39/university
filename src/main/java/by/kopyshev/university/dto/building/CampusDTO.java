package by.kopyshev.university.dto.building;

import by.kopyshev.university.domain.NamedEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class CampusDTO extends NamedEntity {

    @NotBlank
    @Size(min = 1, max = 8)
    protected String number;

    @NotBlank
    @Size(min = 5, max = 100)
    protected String address;

    @Size(max = 1200)
    protected String description;

    public CampusDTO() {
    }

    public CampusDTO(Integer id, String name, String number, String address, String description) {
        super(id, name);
        this.number = number;
        this.address = address;
        this.description = description;
    }

    public CampusDTO(CampusDTO campusDTO) {
        this(campusDTO.id, campusDTO.name, campusDTO.number, campusDTO.address, campusDTO.description);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CampusDTO campusDTO = (CampusDTO) o;
        return Objects.equals(number, campusDTO.number) && Objects.equals(address, campusDTO.address)
                && Objects.equals(description, campusDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, address, description);
    }

    @Override
    public String toString() {
        return "CampusDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
