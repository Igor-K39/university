package by.kopyshev.university.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "study_type")
@Access(AccessType.FIELD)
public class StudyType extends NamedEntity {

    @NotBlank
    @Size(min = 2, max = 8)
    @Column(name = "short_name")
    private String shortName;

    public StudyType() {
    }

    public StudyType(Integer id, String name, String shortName) {
        super(id, name);
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "StudyType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                '}';
    }
}
