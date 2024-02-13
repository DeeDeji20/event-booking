package com.musala.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private final MessageSource messageSource;


    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationMessage ValidationMessageHandleAuthenticationException(Exception ex) {
        System.out.println("exception: "+ex.getMessage()+" "+ex);
        ValidationMessage errorMessage =
                new ValidationMessage(new Date(), 0, ex.getMessage());
        return errorMessage;
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest req) {
        String path = ((ServletWebRequest) req).getRequest().getRequestURI();
        var parsedErrors = parseErrors(ex.getBindingResult().getAllErrors());
        ValidationMessage errorMessage =
                new ValidationMessage(
                        new Date(), 0,parsedErrors.toString());

        return new ResponseEntity<>( errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest req) {
        String path = ((ServletWebRequest) req).getRequest().getRequestURI();
        ValidationMessage errorMessage =
                new ValidationMessage(new Date(), 0, ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        ValidationMessage errorMessage =
                new ValidationMessage(new Date(), 0, ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> conflictExceptionHandler(ConflictException ex, WebRequest req) {
        String path = ((ServletWebRequest) req).getRequest().getRequestURI();
        ValidationMessage errorMessage =
                new ValidationMessage(new Date(), 0, ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private List<String> parseErrors(List<ObjectError> allErrors) {
        return allErrors.stream().map(this::localizeErrorMessage).collect(Collectors.toList());
    }

    private String localizeErrorMessage(ObjectError objectError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(objectError, currentLocale);
        log.info(localizedErrorMessage);
        return localizedErrorMessage;
    }

}
