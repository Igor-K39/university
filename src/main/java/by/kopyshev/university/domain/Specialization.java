package by.kopyshev.university.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "specialization")
public class Specialization extends NamedEntity {

    @NotBlank
    @Size(min = 2, max = 15)
    @Column(name = "code")
    private String code;

    @ManyToOne
    @JoinColumn(name = "speciality_id")
    private Speciality speciality;

    public Specialization() {
    }

    public Specialization(Integer id, String name, String code, Speciality speciality) {
        super(id, name);
        this.code = code;
        this.speciality = speciality;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", speciality=" + speciality +
                '}';
    }
}
