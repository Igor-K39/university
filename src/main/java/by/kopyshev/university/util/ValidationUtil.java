package by.kopyshev.university.util;

import by.kopyshev.university.HasId;
import by.kopyshev.university.exception.ErrorType;
import by.kopyshev.university.exception.IllegalRequestDataException;
import by.kopyshev.university.exception.NotFoundException;
import org.slf4j.Logger;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletRequest;

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

    @NonNull
    public static Throwable getRootCause(@NonNull Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }

    public static String getMessage(Throwable e) {
        return e.getLocalizedMessage() != null ? e.getLocalizedMessage() : e.getClass().getName();
    }

    public static Throwable logAndGetRootCause(Logger log, HttpServletRequest req, Exception e, boolean logStackTrace,
                                               ErrorType errorType) {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        if (logStackTrace) {
            log.error(errorType + " at request " + req.getRequestURL(), rootCause);
        } else {
            log.warn("{} at request  {}: {}", errorType, req.getRequestURL(), rootCause.toString());
        }
        return rootCause;
    }
}
