package by.kopyshev.university.util;

import by.kopyshev.university.HasId;
import by.kopyshev.university.exception.IllegalRequestDataException;
import by.kopyshev.university.exception.NotFoundException;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkNew(HasId bean) {
        if (bean.getId() != null) {
            throw new IllegalArgumentException(bean.getClass().getSimpleName() + ": the id must be null!");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }

    public static HasId checkNotFoundWithId(HasId bean, Class<?> clazz, int id) {
        checkNotFoundWithId(bean != null, clazz, id);
        return bean;
    }

    public static void checkNotFoundWithId(boolean found, Class<?> clazz, int id) {
        if (!found) {
            throw new NotFoundException(clazz, "id = " + id);
        }
    }
}
