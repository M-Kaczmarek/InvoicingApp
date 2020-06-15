package pl.mariuszkaczmarek.invoicingapp.service;

import org.springframework.stereotype.Service;
import pl.mariuszkaczmarek.invoicingapp.model.Invoice;
import pl.mariuszkaczmarek.invoicingapp.repostiory.InvoiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice addInvoice(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> findAll(){
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> findById(Long id){
        return invoiceRepository.findById(id);
    }

    public Invoice updateInvoice(Invoice invoice, Long id){
        if(invoiceRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Can not find this invoice");
        }
        invoice.setId(id);
        return invoiceRepository.save(invoice);
    }
    public void deleteById(Long id){
        invoiceRepository.deleteById(id);
    }
}
