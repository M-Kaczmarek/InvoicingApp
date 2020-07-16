package pl.mariuszkaczmarek.invoicingapp.exception;

public class NotFoundCompanyException extends RuntimeException {

    public NotFoundCompanyException(String message) {
        super(message);
    }
}
