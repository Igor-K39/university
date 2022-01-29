package by.kopyshev.university.domain.education.lecture;

public enum LectureType {

    LECTURE_TIME("Лекция", "ЛК"),
    LABORATORY_TIME("Лабораторна работа", "ЛР"),
    TEST_TIME("Обязательная контрольная работа", "ОКР"),
    CONSULTING_TIME("Консультация", "Консульт."),
    EXAMINE_TIME("Экзамен", "Экз.");

    private final String name;
    private final String shortName;

    LectureType(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }
}
