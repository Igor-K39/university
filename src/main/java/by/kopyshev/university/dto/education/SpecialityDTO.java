package by.kopyshev.university.dto.education;

import by.kopyshev.university.dto.NamedDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class SpecialityDTO extends NamedDTO {

    @NotBlank
    @Size(min = 2, max = 15)
    private String code;

    @NotBlank
    @Size(min = 2, max = 10)
    private String shortName;

    private Integer facultyId;

    public SpecialityDTO() {
    }

    public SpecialityDTO(Integer id, String name, String code, String shortName, Integer facultyId) {
        super(id, name);
        this.code = code;
        this.shortName = shortName;
        this.facultyId = facultyId;
    }

    public SpecialityDTO(SpecialityDTO specialityDTO) {
        this(specialityDTO.id, specialityDTO.name, specialityDTO.code, specialityDTO.shortName, specialityDTO.facultyId);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpecialityDTO)) return false;
        if (!super.equals(o)) return false;
        SpecialityDTO that = (SpecialityDTO) o;
        return Objects.equals(getCode(), that.getCode())
                && Objects.equals(getShortName(), that.getShortName())
                && Objects.equals(getFacultyId(), that.getFacultyId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCode(), getShortName(), getFacultyId());
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", shortName='" + shortName + '\'' +
                ", faculty=" + facultyId +
                '}';
    }
}
