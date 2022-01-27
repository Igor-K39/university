package by.kopyshev.university.domain.education.lecture;

import by.kopyshev.university.domain.NamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "lecture_type")
@Access(AccessType.FIELD)
public class LectureType extends NamedEntity {

    @NotBlank
    @Size(min = 2, max = 8)
    @Column(name = "short_name")
    private String shortName;

    public LectureType() {
    }

    public LectureType(Integer id, String name, String shortName) {
        super(id, name);
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "LectureType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }
}
