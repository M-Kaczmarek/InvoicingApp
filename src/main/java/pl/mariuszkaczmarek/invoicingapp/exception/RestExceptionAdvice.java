package pl.mariuszkaczmarek.invoicingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundInvoiceException.class, NotFoundCompanyException.class})
    ResponseEntity<Error> notFoundHandler(Exception e) {
        Error error = new Error(e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ExistInvoiceException.class, ExistCompanyException.class})
    ResponseEntity<Error> conflictHandler(Exception e) {
        Error error = new Error(e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
