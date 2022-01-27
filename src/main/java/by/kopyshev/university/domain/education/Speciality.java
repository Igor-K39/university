package by.kopyshev.university.domain.education;

import by.kopyshev.university.domain.NamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "speciality")
@Access(AccessType.FIELD)
public class Speciality extends NamedEntity {

    @NotBlank
    @Size(min = 2, max = 15)
    @Column(name = "code")
    private String code;

    @NotBlank
    @Size(min = 2, max = 10)
    @Column(name = "short_name")
    private String shortName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality")
    private List<Specialization> specializations;

    public Speciality() {
    }

    public Speciality(Integer id, String name, String code, String shortName, Faculty faculty) {
        super(id, name);
        this.code = code;
        this.shortName = shortName;
        this.faculty = faculty;
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", shortName='" + shortName + '\'' +
                ", faculty=" + faculty +
                '}';
    }
}
