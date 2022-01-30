package by.kopyshev.university.dto.education.group;

import by.kopyshev.university.domain.education.StudyType;
import by.kopyshev.university.dto.education.SpecialityDTO;
import by.kopyshev.university.dto.education.role.EducatorDTO;
import by.kopyshev.university.dto.education.role.StudentDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class StudentGroupWithStudentsDTO extends StudentGroupDTO {

    List<StudentDTO> studentDTOs;

    public StudentGroupWithStudentsDTO() {
    }

    public StudentGroupWithStudentsDTO(Integer id, String name, SpecialityDTO specialityDTO, StudyType studyType,
                                       Integer currentEducationYear, LocalDate admission, EducatorDTO curator,
                                       List<StudentDTO> studentDTOs) {
        super(id, name, specialityDTO, studyType, currentEducationYear, admission, curator);
        this.studentDTOs = studentDTOs;
    }

    public List<StudentDTO> getStudentDTOs() {
        return studentDTOs;
    }

    public void setStudentDTOs(List<StudentDTO> students) {
        this.studentDTOs = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentGroupWithStudentsDTO)) return false;
        if (!super.equals(o)) return false;
        StudentGroupWithStudentsDTO that = (StudentGroupWithStudentsDTO) o;
        return Objects.equals(getStudentDTOs(), that.getStudentDTOs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStudentDTOs());
    }

    @Override
    public String toString() {
        return "StudentGroupWithStudentsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialityDTO=" + specialityDTO +
                ", studyType=" + studyType +
                ", currentEducationYear=" + currentEducationYear +
                ", admission=" + admission +
                ", curator=" + curator +
                ", students=" + studentDTOs +
                '}';
    }
}
