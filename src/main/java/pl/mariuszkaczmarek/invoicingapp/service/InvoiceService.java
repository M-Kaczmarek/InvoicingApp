package pl.mariuszkaczmarek.invoicingapp.service;

import org.springframework.stereotype.Service;
import pl.mariuszkaczmarek.invoicingapp.model.Invoice;
import pl.mariuszkaczmarek.invoicingapp.repostiory.InvoiceRepository;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice addInvoice(Invoice invoice){
       return invoiceRepository.save(invoice);
    }
    public Iterable<Invoice> findAll(){
        return invoiceRepository.findAll();
    }
}
