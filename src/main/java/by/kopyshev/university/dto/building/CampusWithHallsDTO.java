package by.kopyshev.university.dto.building;

import java.util.List;
import java.util.Objects;

public class CampusWithHallsDTO extends CampusDTO {

    private List<LectureHallDTO> lectureHallsDTO;

    public List<LectureHallDTO> getLectureHallsDTO() {
        return List.copyOf(lectureHallsDTO);
    }

    public void setLectureHallsDTO(List<LectureHallDTO> lectureHallsDTO) {
        this.lectureHallsDTO = List.copyOf(lectureHallsDTO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CampusWithHallsDTO)) return false;
        if (!super.equals(o)) return false;
        CampusWithHallsDTO that = (CampusWithHallsDTO) o;
        return Objects.equals(getLectureHallsDTO(), that.getLectureHallsDTO());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLectureHallsDTO());
    }

    @Override
    public String toString() {
        return "CampusWithHallsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", lectureHallsDTO=" + lectureHallsDTO +
                '}';
    }
}
