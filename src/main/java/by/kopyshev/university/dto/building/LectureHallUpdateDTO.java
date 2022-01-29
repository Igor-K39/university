package by.kopyshev.university.dto.building;

import by.kopyshev.university.domain.building.LectureHallType;
import by.kopyshev.university.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class LectureHallUpdateDTO extends BaseDTO {

    @NotBlank
    @Size(min = 1, max = 8)
    private String number;

    @NotNull
    private Integer campusId;

    @NotNull
    private LectureHallType type;

    @NotNull
    private Integer capacity;

    @Size(max = 1200)
    private String description;

    public LectureHallUpdateDTO() {
    }

    public LectureHallUpdateDTO(Integer id, String number, Integer campusId, LectureHallType type, Integer capacity,
                                String description) {
        super(id);
        this.number = number;
        this.campusId = campusId;
        this.type = type;
        this.capacity = capacity;
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }

    public LectureHallType getType() {
        return type;
    }

    public void setType(LectureHallType type) {
        this.type = type;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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
        LectureHallUpdateDTO that = (LectureHallUpdateDTO) o;
        return Objects.equals(number, that.number) && Objects.equals(campusId, that.campusId) && type == that.type &&
                Objects.equals(capacity, that.capacity) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, campusId, type, capacity, description);
    }

    @Override
    public String toString() {
        return "LectureHallUpdateDTO{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", campusId=" + campusId +
                ", type=" + type +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                '}';
    }
}
