package by.kopyshev.university.dto.education.student;

import by.kopyshev.university.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class StudentUpdateDTO extends BaseDTO {

    @NotBlank
    @Size(min = 6, max = 15)
    private String recordBookNumber;

    private Integer personId;

    private Integer studentGroupId;

    private boolean leader = false;

    public StudentUpdateDTO() {
    }

    public StudentUpdateDTO(Integer id, String recordBookNumber, Integer personId, Integer studentGroupId) {
        super(id);
        this.recordBookNumber = recordBookNumber;
        this.personId = personId;
        this.studentGroupId = studentGroupId;
    }

    public String getRecordBookNumber() {
        return recordBookNumber;
    }

    public void setRecordBookNumber(String recordBookNumber) {
        this.recordBookNumber = recordBookNumber;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
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
        if (!(o instanceof StudentUpdateDTO)) return false;
        if (!super.equals(o)) return false;
        StudentUpdateDTO that = (StudentUpdateDTO) o;
        return isLeader() == that.isLeader()
                && Objects.equals(getRecordBookNumber(), that.getRecordBookNumber())
                && Objects.equals(getPersonId(), that.getPersonId())
                && Objects.equals(getStudentGroupId(), that.getStudentGroupId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRecordBookNumber(), getPersonId(), getStudentGroupId(), isLeader());
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", recordBookNumber='" + recordBookNumber + '\'' +
                ", person=" + personId +
                ", studentGroup=" + studentGroupId +
                ", leader=" + leader +
                '}';
    }
}
