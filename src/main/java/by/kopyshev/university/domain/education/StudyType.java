package by.kopyshev.university.domain.education;

public enum StudyType {
    FULL_TIME("Дневная форма обучения", "Дневная"),
    PART_TIME("Заочная форма обучения", "Заочная"),
    DISTANCE("Дистанционная форма обучения", "Дистанционная");

    private final String name;
    private final String shortName;

    StudyType(String name, String shortName) {
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
