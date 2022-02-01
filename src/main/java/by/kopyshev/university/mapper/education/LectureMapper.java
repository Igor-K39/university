package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.building.LectureHall;
import by.kopyshev.university.domain.education.StudentGroup;
import by.kopyshev.university.domain.education.lecture.Discipline;
import by.kopyshev.university.domain.education.lecture.Lecture;
import by.kopyshev.university.domain.education.role.Educator;
import by.kopyshev.university.dto.education.lecture.LectureDTO;
import by.kopyshev.university.dto.education.lecture.LectureUpdateDTO;
import by.kopyshev.university.exception.NotFoundException;
import by.kopyshev.university.repository.building.LectureHallRepository;
import by.kopyshev.university.repository.education.DisciplineRepository;
import by.kopyshev.university.repository.education.EducatorRepository;
import by.kopyshev.university.repository.education.StudentGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
public class LectureMapper {
    private final ModelMapper facultyDepartmentMapper = new ModelMapper();
    private final DisciplineMapper disciplineMapper;
    private final DisciplineRepository disciplineRepository;
    private final LectureHallRepository lectureHallRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final EducatorRepository educatorRepository;

    public LectureMapper(DisciplineMapper disciplineMapper, DisciplineRepository disciplineRepository,
                         LectureHallRepository lectureHallRepository, StudentGroupRepository studentGroupRepository,
                         EducatorRepository educatorRepository) {
        this.disciplineMapper = disciplineMapper;
        this.disciplineRepository = disciplineRepository;
        this.lectureHallRepository = lectureHallRepository;
        this.studentGroupRepository = studentGroupRepository;
        this.educatorRepository = educatorRepository;
    }

    @PostConstruct
    public void setup() {
        facultyDepartmentMapper.createTypeMap(LectureUpdateDTO.class, Lecture.class)
                .addMappings(mapper -> mapper.skip(Lecture::setDiscipline))
                .addMappings(mapper -> mapper.skip(Lecture::setLectureHall))
                .addMappings(mapper -> mapper.skip(Lecture::setEducator))
                .addMappings(mapper -> mapper.skip(Lecture::setStudentGroup))
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();

                    var disciplineId = source.getDisciplineId();
                    var discipline = disciplineRepository.findById(disciplineId).orElseThrow(
                            () -> new NotFoundException(Discipline.class, "id = " + disciplineId));
                    destination.setDiscipline(discipline);

                    var lectureHallId = source.getLectureHallId();
                    var lectureHall = lectureHallRepository.findById(lectureHallId).orElseThrow(
                            () -> new NotFoundException(LectureHall.class, "id = " + lectureHallId));
                    destination.setLectureHall(lectureHall);

                    var educatorId = source.getEducatorId();
                    var educator = educatorRepository.findById(educatorId).orElseThrow(
                            () -> new NotFoundException(Educator.class, "id = " + educatorId));
                    destination.setEducator(educator);

                    var studentGroupId = source.getStudentGroupId();
                    var studentGroup = studentGroupRepository.findById(studentGroupId).orElseThrow(
                            () -> new NotFoundException(StudentGroup.class, "id = " + studentGroupId));
                    destination.setStudentGroup(studentGroup);
                    return destination;
                });

        facultyDepartmentMapper.createTypeMap(Lecture.class, LectureDTO.class)
                .setPostConverter(ctx -> {
                    var source = ctx.getSource();
                    var destination = ctx.getDestination();

                    destination.setDisciplineDTO(disciplineMapper.toDTO(source.getDiscipline()));
                    destination.setLectureHallNumber(source.getLectureHall().getNumber());
                    destination.setCampusNumber(source.getLectureHall().getCampus().getNumber());
                    destination.setEducator(source.getEducator().getPerson().getShortName());

                    StudentGroup group = source.getStudentGroup();
                    destination.setStudentGroupName(group.getName());
                    destination.setStudentGroupId(group.getId());
                    return destination;
                });
    }

    public Lecture toEntity(LectureUpdateDTO lectureUpdateDTO) {
        return isNull(lectureUpdateDTO)
                ? null
                : facultyDepartmentMapper.map(lectureUpdateDTO, Lecture.class);
    }

    public LectureDTO toDTO(Lecture lecture) {
        return isNull(lecture)
                ? null
                : facultyDepartmentMapper.map(lecture, LectureDTO.class);
    }

    public List<LectureDTO> toDTO(List<Lecture> lectures) {
        return isNull(lectures)
                ? null
                : lectures.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
