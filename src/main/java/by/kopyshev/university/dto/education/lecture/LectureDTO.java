package by.kopyshev.university.dto.education.lecture;

import by.kopyshev.university.domain.education.lecture.LectureType;
import by.kopyshev.university.dto.BaseDTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

public class LectureDTO extends BaseDTO {

    private DisciplineDTO disciplineDTO;

    @NotNull
    private LectureType lectureType;

    private String lectureHallNumber;

    private String campusNumber;

    private String educator;

    private String studentGroupName;

    private Integer studentGroupId;

    @NotNull
    private LocalDate date;

    public LectureDTO() {
    }

    public LectureDTO(Integer id, DisciplineDTO disciplineDTO, LectureType lectureType, String lectureHallNumber,
                      String campusNumber, String educator, String studentGroupName, Integer studentGroupId,
                      LocalDate date) {
        super(id);
        this.disciplineDTO = disciplineDTO;
        this.lectureType = lectureType;
        this.lectureHallNumber = lectureHallNumber;
        this.campusNumber = campusNumber;
        this.educator = educator;
        this.studentGroupName = studentGroupName;
        this.studentGroupId = studentGroupId;
        this.date = date;
    }

    public DisciplineDTO getDisciplineDTO() {
        return disciplineDTO;
    }

    public void setDisciplineDTO(DisciplineDTO disciplineDTO) {
        this.disciplineDTO = disciplineDTO;
    }

    public LectureType getLectureType() {
        return lectureType;
    }

    public void setLectureType(LectureType lectureType) {
        this.lectureType = lectureType;
    }

    public String getLectureHallNumber() {
        return lectureHallNumber;
    }

    public void setLectureHallNumber(String lectureHallNumber) {
        this.lectureHallNumber = lectureHallNumber;
    }

    public String getCampusNumber() {
        return campusNumber;
    }

    public void setCampusNumber(String campusNumber) {
        this.campusNumber = campusNumber;
    }

    public String getEducator() {
        return educator;
    }

    public void setEducator(String educator) {
        this.educator = educator;
    }

    public String getStudentGroupName() {
        return studentGroupName;
    }

    public void setStudentGroupName(String studentGroupName) {
        this.studentGroupName = studentGroupName;
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
        if (!(o instanceof LectureDTO)) return false;
        if (!super.equals(o)) return false;
        LectureDTO that = (LectureDTO) o;
        return Objects.equals(disciplineDTO, that.disciplineDTO)
                && lectureType == that.lectureType
                && Objects.equals(lectureHallNumber, that.lectureHallNumber)
                && Objects.equals(campusNumber, that.campusNumber)
                && Objects.equals(educator, that.educator)
                && Objects.equals(studentGroupName, that.studentGroupName)
                && Objects.equals(studentGroupId, that.studentGroupId) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), disciplineDTO, lectureType, lectureHallNumber, campusNumber,
                educator, studentGroupName, studentGroupId, date);
    }

    @Override
    public String toString() {
        return "LectureDTO{" +
                "id=" + id +
                ", disciplineDTO=" + disciplineDTO +
                ", lectureType=" + lectureType +
                ", lectureHallNumber='" + lectureHallNumber + '\'' +
                ", campusNumber='" + campusNumber + '\'' +
                ", educatorName='" + educator + '\'' +
                ", studentGroupName='" + studentGroupName + '\'' +
                ", studentGroupId=" + studentGroupId +
                ", date=" + date +
                '}';
    }
}
