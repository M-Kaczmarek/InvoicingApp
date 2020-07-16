package pl.mariuszkaczmarek.invoicingapp.exception;

public class ExistInvoiceException extends RuntimeException {

    public ExistInvoiceException(String message) {
        super(message);
    }
}
