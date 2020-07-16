package pl.mariuszkaczmarek.invoicingapp.exception;

public class ExistCompanyException extends RuntimeException {

    public ExistCompanyException(String message) {
        super(message);
    }
}
