package pl.mariuszkaczmarek.invoicingapp.model;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public class TransportCompany extends Company {
    private boolean internationalTransport;

    public boolean isInternationalTransport() {
        return internationalTransport;
    }

    public void setInternationalTransport(boolean internationalTransport) {
        this.internationalTransport = internationalTransport;
    }

    @Override
    public void setInvoices(Set<Invoice> invoices) {
        super.setInvoices(invoices);
        for (Invoice invoice : invoices) {
            invoice.setCompany(this);
        }
    }
}
