package by.kopyshev.university.domain.building;

import by.kopyshev.university.domain.NamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "campus")
@Access(AccessType.FIELD)
public class Campus extends NamedEntity {

    @NotBlank
    @Size(min = 1, max = 8)
    @Column(name = "number")
    private String number;

    @NotBlank
    @Size(min = 5, max = 100)
    @Column(name = "address")
    private String address;

    @Size(max = 1200)
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "campus")
    private List<LectureHall> lectureHallList;

    public Campus() {
    }

    public Campus(Integer id, String name, String number, String address, String description) {
        super(id, name);
        this.number = number;
        this.address = address;
        this.description = description;
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
    public String toString() {
        return "Campus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
