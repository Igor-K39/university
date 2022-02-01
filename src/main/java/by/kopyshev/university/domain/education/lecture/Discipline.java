package by.kopyshev.university.domain.education.lecture;

import by.kopyshev.university.domain.NamedEntity;
import by.kopyshev.university.domain.education.FacultyDepartment;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NamedEntityGraph(name = "discipline-with-department", attributeNodes = {
        @NamedAttributeNode(value = "facultyDepartment")
})
@Entity
@Table(name = "discipline")
@Access(AccessType.FIELD)
public class Discipline extends NamedEntity {

    @NotBlank
    @Size(min = 2, max = 20)
    @Column(name = "short_name")
    private String shortName;

    @ManyToOne
    @JoinColumn(name = "faculty_department_id")
    private FacultyDepartment facultyDepartment;

    public Discipline() {
    }

    public Discipline(Integer id, String name, String shortName, FacultyDepartment facultyDepartment) {
        super(id, name);
        this.shortName = shortName;
        this.facultyDepartment = facultyDepartment;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public FacultyDepartment getFacultyDepartment() {
        return facultyDepartment;
    }

    public void setFacultyDepartment(FacultyDepartment facultyDepartment) {
        this.facultyDepartment = facultyDepartment;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", facultyDepartment=" + facultyDepartment +
                '}';
    }
}
