package by.kopyshev.university.web;

import by.kopyshev.university.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SecurityUtil {

    public static int authUserId() {
        return get().getId();
    }

    public static User get() {
        return Objects.requireNonNull(safeGet(), "No authorized user found");
    }

    public static User safeGet() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        return (principal instanceof User) ? (User) principal : null;
    }
}
