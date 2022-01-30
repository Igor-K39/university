package by.kopyshev.university.dto.education.role;

import by.kopyshev.university.dto.BaseDTO;
import by.kopyshev.university.dto.PersonDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class StudentDTO extends BaseDTO {

    @NotBlank
    @Size(min = 6, max = 15)
    private String recordBookNumber;

    @NotNull
    private PersonDTO personDTO;

    private Integer studentGroupId;

    private boolean leader = false;

    public StudentDTO() {
    }

    public StudentDTO(Integer id, String recordBookNumber, PersonDTO personDTO, Integer studentGroupId) {
        super(id);
        this.recordBookNumber = recordBookNumber;
        this.personDTO = personDTO;
        this.studentGroupId = studentGroupId;
    }

    public String getRecordBookNumber() {
        return recordBookNumber;
    }

    public void setRecordBookNumber(String recordBookNumber) {
        this.recordBookNumber = recordBookNumber;
    }

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }

    public Integer getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(Integer studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    public boolean isLeader() {
        return leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDTO)) return false;
        if (!super.equals(o)) return false;
        StudentDTO that = (StudentDTO) o;
        return isLeader() == that.isLeader()
                && Objects.equals(getRecordBookNumber(), that.getRecordBookNumber())
                && Objects.equals(getPersonDTO(), that.getPersonDTO())
                && Objects.equals(getStudentGroupId(), that.getStudentGroupId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRecordBookNumber(), getPersonDTO(), getStudentGroupId(), isLeader());
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", recordBookNumber='" + recordBookNumber + '\'' +
                ", person=" + personDTO +
                ", studentGroup=" + studentGroupId +
                ", leader=" + leader +
                '}';
    }
}
