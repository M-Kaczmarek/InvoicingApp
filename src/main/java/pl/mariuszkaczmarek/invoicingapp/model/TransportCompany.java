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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransportCompany)) return false;

        TransportCompany that = (TransportCompany) o;

        return internationalTransport == that.internationalTransport;
    }

    @Override
    public int hashCode() {
        return (internationalTransport ? 1 : 0);
    }
}
