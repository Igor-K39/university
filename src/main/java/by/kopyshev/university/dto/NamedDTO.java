package by.kopyshev.university.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public abstract class NamedDTO extends BaseDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    protected String name;

    public NamedDTO() {
    }

    public NamedDTO(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NamedDTO namedDTO = (NamedDTO) o;
        return Objects.equals(name, namedDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "NamedDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
