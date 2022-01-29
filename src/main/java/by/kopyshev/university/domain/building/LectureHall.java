package by.kopyshev.university.domain.building;

import by.kopyshev.university.domain.BaseEntity;
import by.kopyshev.university.domain.education.role.Educator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "lecture_hall")
public class LectureHall extends BaseEntity {

    @NotBlank
    @Size(min = 1, max = 8)
    @Column(name = "number")
    private String number;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "campus_id")
    private Campus campus;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private LectureHallType type;

    @NotNull
    @Column(name = "capacity")
    private Integer capacity;

    @Size(max = 1200)
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lectureHall")
    private List<Educator> educators;

    public LectureHall() {
    }

    public LectureHall(Integer id, String number, Campus campus, LectureHallType type, Integer capacity,
                       String description) {
        super(id);
        this.number = number;
        this.campus = campus;
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

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
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
    public String toString() {
        return "LectureHall{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", campus=" + campus +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                '}';
    }
}
