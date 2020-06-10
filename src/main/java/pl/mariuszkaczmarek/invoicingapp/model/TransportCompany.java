package pl.mariuszkaczmarek.invoicingapp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Set;

@Entity
@JsonTypeName("transportCompany")
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
