package pl.mariuszkaczmarek.invoicingapp.service;

import org.springframework.stereotype.Service;
import pl.mariuszkaczmarek.invoicingapp.exception.ExistInvoiceException;
import pl.mariuszkaczmarek.invoicingapp.exception.NotFoundInvoiceException;
import pl.mariuszkaczmarek.invoicingapp.model.Invoice;
import pl.mariuszkaczmarek.invoicingapp.repostiory.InvoiceRepository;

import java.util.List;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice addInvoice(Invoice invoice) {
        if (invoiceRepository.existsByNameAndSurrName(invoice.getName(), invoice.getSurrName())) {
            throw new ExistInvoiceException("Invoice already exist");
        }

        return invoiceRepository.save(invoice);
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Invoice findById(Long id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new NotFoundInvoiceException("Invoice not found"));
    }

    public Invoice updateInvoice(Invoice invoice, Long id) {
        invoiceRepository.findById(id).orElseThrow(() -> new NotFoundInvoiceException("Invoice not found"));
        invoice.setId(id);

        return invoiceRepository.save(invoice);
    }

    public void deleteById(Long id) {
        invoiceRepository.findById(id).orElseThrow(() -> new NotFoundInvoiceException("Invoice not found"));
        invoiceRepository.deleteById(id);
    }
}
