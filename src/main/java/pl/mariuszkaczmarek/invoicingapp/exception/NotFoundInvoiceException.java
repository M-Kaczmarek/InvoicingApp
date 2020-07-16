package pl.mariuszkaczmarek.invoicingapp.exception;

public class NotFoundInvoiceException extends RuntimeException {

    public NotFoundInvoiceException(String message) {
        super(message);
    }
}
