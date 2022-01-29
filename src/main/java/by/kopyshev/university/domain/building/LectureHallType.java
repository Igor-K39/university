package by.kopyshev.university.domain.building;

public enum LectureHallType {
    LECTURE("Аудитория лекционного типа"),
    LABORATORY("Аудитория лабораторного типа");

    private final String name;

    LectureHallType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
