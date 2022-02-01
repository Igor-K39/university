package by.kopyshev.university.dto.education.student;

import by.kopyshev.university.dto.NamedDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class StudentPreviewDTO extends NamedDTO {

    @NotBlank
    @Size(min = 6, max = 15)
    private String recordBookNumber;

    private Integer personId;

    private Integer studentGroupId;

    private boolean leader = false;

    public StudentPreviewDTO() {
    }

    public StudentPreviewDTO(Integer id, String name, String recordBookNumber, Integer personId, Integer studentGroupId,
                             boolean leader) {
        super(id, name);
        this.recordBookNumber = recordBookNumber;
        this.personId = personId;
        this.studentGroupId = studentGroupId;
        this.leader = leader;
    }

    public StudentPreviewDTO(StudentDTO studentDTO) {
        this(studentDTO.getId(), studentDTO.getFullName(), studentDTO.getRecordBookNumber(),
                studentDTO.getPersonDTO().getId(), studentDTO.getStudentGroupId(), studentDTO.isLeader());
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
        if (!(o instanceof StudentPreviewDTO)) return false;
        if (!super.equals(o)) return false;
        StudentPreviewDTO that = (StudentPreviewDTO) o;
        return isLeader() == that.isLeader()
                && Objects.equals(getRecordBookNumber(), that.getRecordBookNumber())
                && Objects.equals(getPersonId(), that.getPersonId())
                && Objects.equals(getStudentGroupId(), that.getStudentGroupId());
    }

    @Override
    public String toString() {
        return "StudentPreviewDTO{" +
                "id=" + id +
                ", recordBookNumber='" + recordBookNumber + '\'' +
                ", personId=" + personId +
                ", studentGroupId=" + studentGroupId +
                ", leader=" + leader +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRecordBookNumber(), getPersonId(), getStudentGroupId(), isLeader());
    }
}
