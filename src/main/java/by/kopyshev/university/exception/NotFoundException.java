package by.kopyshev.university.exception;

public class NotFoundException extends RuntimeException {

    private final String className;
    private final String attributeMessage;

    public NotFoundException(Class<?> clazz, String attributeMessage) {
        super("Not found " + clazz.getSimpleName() + ": " + attributeMessage);
        this.className = clazz.getSimpleName();
        this.attributeMessage = attributeMessage;
    }

    public String getClassName() {
        return className;
    }

    public String getAttributeMessage() {
        return attributeMessage;
    }
}