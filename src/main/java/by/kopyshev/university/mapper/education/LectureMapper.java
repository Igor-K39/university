package by.kopyshev.university.mapper.education;

import by.kopyshev.university.domain.Person;
import by.kopyshev.university.domain.education.StudentGroup;
import by.kopyshev.university.domain.education.lecture.Lecture;
import by.kopyshev.university.dto.education.lecture.LectureDTO;
import by.kopyshev.university.dto.education.lecture.LectureUpdateDTO;
import by.kopyshev.university.mapper.building.LectureHallMapper;
import by.kopyshev.university.repository.building.LectureHallRepository;
import by.kopyshev.university.repository.education.DisciplineRepository;
import by.kopyshev.university.repository.education.EducatorRepository;
import by.kopyshev.university.repository.education.FacultyDepartmentRepository;
import by.kopyshev.university.repository.education.StudentGroupRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LectureMapper {
    private final ModelMapper facultyDepartmentMapper = new ModelMapper();
    private final DisciplineMapper disciplineMapper;
    private final LectureHallMapper lectureHallMapper;
    private final StudentGroupMapper studentGroupMapper;
    private final DisciplineRepository disciplineRepository;
    private final LectureHallRepository lectureHallRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final EducatorRepository educatorRepository;
    private final FacultyDepartmentRepository facultyDepartmentRepository;

    public LectureMapper(DisciplineMapper disciplineMapper, LectureHallMapper lectureHallMapper,
                         StudentGroupMapper studentGroupMapper, DisciplineRepository disciplineRepository,
                         LectureHallRepository lectureHallRepository, StudentGroupRepository studentGroupRepository,
                         EducatorRepository educatorRepository, FacultyDepartmentRepository facultyDepartmentRepository) {
        this.disciplineMapper = disciplineMapper;
        this.lectureHallMapper = lectureHallMapper;
        this.studentGroupMapper = studentGroupMapper;
        this.disciplineRepository = disciplineRepository;
        this.lectureHallRepository = lectureHallRepository;
        this.studentGroupRepository = studentGroupRepository;
        this.educatorRepository = educatorRepository;
        this.facultyDepartmentRepository = facultyDepartmentRepository;
    }

    @PostConstruct
    public void setup() {
        Converter<LectureUpdateDTO, Lecture> toEntityPostConverter = ctx -> {
            LectureUpdateDTO source = ctx.getSource();
            Lecture destination = ctx.getDestination();
            destination.setDiscipline(disciplineRepository.getById(source.getDisciplineId()));
            destination.setLectureHall(lectureHallRepository.getById(source.getLectureHallId()));
            destination.setEducator(educatorRepository.getById(source.getEducatorId()));
            destination.setStudentGroup(studentGroupRepository.getById(source.getStudentGroupId()));
            return ctx.getDestination();
        };
        facultyDepartmentMapper.createTypeMap(LectureUpdateDTO.class, Lecture.class)
                .addMappings(mapper -> mapper.skip(Lecture::setDiscipline))
                .addMappings(mapper -> mapper.skip(Lecture::setLectureHall))
                .addMappings(mapper -> mapper.skip(Lecture::setEducator))
                .addMappings(mapper -> mapper.skip(Lecture::setStudentGroup))
                .setPostConverter(toEntityPostConverter);

        Converter<Lecture, LectureDTO> toDTOPostConverter = ctx -> {
            Lecture source = ctx.getSource();
            LectureDTO destination = ctx.getDestination();

            destination.setDisciplineDTO(disciplineMapper.toDTO(source.getDiscipline()));
            destination.setLectureHallNumber(source.getLectureHall().getNumber());
            destination.setCampusNumber(source.getLectureHall().getCampus().getNumber());

            Person educatorPerson = source.getEducator().getPerson();
            String educator = educatorPerson.getLastName() + " "
                    + educatorPerson.getFirstName().substring(0, 0) + ". "
                    + educatorPerson.getMiddleName().substring(0, 0) + "., "
                    + source.getEducator().getPosition();
            destination.setEducator(educator);

            StudentGroup group = source.getStudentGroup();
            destination.setStudentGroupName(group.getName());
            destination.setStudentGroupId(group.getId());
            return destination;
        };
        facultyDepartmentMapper.createTypeMap(Lecture.class, LectureDTO.class)
                .setPostConverter(toDTOPostConverter);
    }

    public Lecture toEntity(LectureUpdateDTO lectureUpdateDTO) {
        return facultyDepartmentMapper.map(lectureUpdateDTO, Lecture.class);
    }

    public LectureDTO toDTO(Lecture lecture) {
        return facultyDepartmentMapper.map(lecture, LectureDTO.class);
    }

    public List<LectureDTO> toDTO(List<Lecture> lectures) {
        return lectures.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
