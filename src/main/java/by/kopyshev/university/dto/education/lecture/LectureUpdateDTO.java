package by.kopyshev.university.dto.education.lecture;

import by.kopyshev.university.domain.education.lecture.LectureType;
import by.kopyshev.university.dto.BaseDTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class LectureUpdateDTO extends BaseDTO {

    private Integer disciplineId;

    @NotNull
    private LectureType lectureType;

    private Integer lectureHallId;

    private Integer educatorId;

    private Integer studentGroupId;

    @NotNull
    private LocalDate date;

    public LectureUpdateDTO() {
    }

    public LectureUpdateDTO(Integer id, Integer disciplineId, LectureType lectureType, Integer lectureHallId,
                            Integer educatorId, Integer studentGroupId, LocalDate date) {
        super(id);
        this.disciplineId = disciplineId;
        this.lectureType = lectureType;
        this.lectureHallId = lectureHallId;
        this.educatorId = educatorId;
        this.studentGroupId = studentGroupId;
        this.date = date;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    public LectureType getLectureType() {
        return lectureType;
    }

    public void setLectureType(LectureType lectureType) {
        this.lectureType = lectureType;
    }

    public Integer getLectureHallId() {
        return lectureHallId;
    }

    public void setLectureHallId(Integer lectureHallId) {
        this.lectureHallId = lectureHallId;
    }

    public Integer getEducatorId() {
        return educatorId;
    }

    public void setEducatorId(Integer educatorId) {
        this.educatorId = educatorId;
    }

    public Integer getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(Integer studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LectureUpdateDTO)) return false;
        if (!super.equals(o)) return false;
        LectureUpdateDTO that = (LectureUpdateDTO) o;
        return Objects.equals(getDisciplineId(), that.getDisciplineId())
                && getLectureType() == that.getLectureType()
                && Objects.equals(getLectureHallId(), that.getLectureHallId())
                && Objects.equals(getEducatorId(), that.getEducatorId())
                && Objects.equals(getStudentGroupId(), that.getStudentGroupId())
                && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDisciplineId(), getLectureType(),
                getLectureHallId(), getEducatorId(), getStudentGroupId(), getDate());
    }

    @Override
    public String toString() {
        return "LectureUpdateDTO{" +
                "id=" + id +
                ", disciplineId=" + disciplineId +
                ", lectureType=" + lectureType +
                ", lectureHallId=" + lectureHallId +
                ", educatorId=" + educatorId +
                ", studentGroupId=" + studentGroupId +
                ", date=" + date +
                '}';
    }
}
