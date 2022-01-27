package by.kopyshev.university.web;

import by.kopyshev.university.exception.ErrorInfo;
import by.kopyshev.university.exception.ErrorType;
import by.kopyshev.university.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static by.kopyshev.university.exception.ErrorType.DATA_NOT_FOUND;
import static by.kopyshev.university.exception.ErrorType.VALIDATION_ERROR;
import static by.kopyshev.university.util.ValidationUtil.getMessage;
import static by.kopyshev.university.util.ValidationUtil.logAndGetRootCause;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {
    private static final Logger log = LoggerFactory.getLogger(ExceptionInfoHandler.class);
    private final MessageSourceAccessor messageSourceAccessor;

    public static final String EXCEPTION_DUPLICATE_CAMPUS = "exception.campus.duplicateName";

    public ExceptionInfoHandler(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfo> handleNotFoundException(HttpServletRequest request, NotFoundException exception) {
        String notExists = messageSourceAccessor.getMessage("exception.notExists");
        String entityName = messageSourceAccessor.getMessage(exception.getClassName());
        String message = entityName + " (" + exception.getAttributeMessage() + ") " + notExists;
        return logAndGetErrorInfo(request, exception, false, DATA_NOT_FOUND, message);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorInfo> handleBindException(HttpServletRequest request, BindException exception) {
        String[] details = exception.getBindingResult().getFieldErrors().stream()
                .map(messageSourceAccessor::getMessage)
                .toArray(String[]::new);
        return logAndGetErrorInfo(request, exception, false, VALIDATION_ERROR, details);
    }

    private ResponseEntity<ErrorInfo> logAndGetErrorInfo(HttpServletRequest request, Exception exception,
                                                         boolean logStackTrace, ErrorType errorType, String... details) {
        Throwable rootCause = logAndGetRootCause(log, request, exception, logStackTrace, errorType);
        return ResponseEntity.status(errorType.getStatus())
                .body(new ErrorInfo(request.getRequestURL().toString(), errorType,
                        messageSourceAccessor.getMessage(errorType.getErrorCode()),
                        details.length != 0 ? details : new String[]{ getMessage(rootCause) })
                );
    }
}
