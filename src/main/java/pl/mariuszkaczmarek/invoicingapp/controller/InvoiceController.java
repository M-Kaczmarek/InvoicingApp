package pl.mariuszkaczmarek.invoicingapp.controller;

import org.springframework.web.bind.annotation.*;
import pl.mariuszkaczmarek.invoicingapp.model.Invoice;
import pl.mariuszkaczmarek.invoicingapp.service.TransportCompanyService;
import pl.mariuszkaczmarek.invoicingapp.service.InvoiceService;

import java.util.Optional;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    private TransportCompanyService transportCompanyService;
    private InvoiceService invoiceService;

    public InvoiceController(TransportCompanyService transportCompanyService, InvoiceService invoiceService) {
        this.transportCompanyService = transportCompanyService;
        this.invoiceService = invoiceService;
    }
    @GetMapping
    public Iterable<Invoice> findAll(){
        return invoiceService.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Invoice> findById(@RequestParam Long id){
        return invoiceService.findById(id);
    }

    @PostMapping
    public Invoice save(@RequestBody Invoice invoice){
        return invoiceService.addInvoice(invoice);
    }

    @PutMapping
    public Invoice updateInvoice(@RequestBody Invoice invoice){
        return invoiceService.updateInvoice(invoice);
    }
    @DeleteMapping
    public void deleteById(@RequestParam Long id){

        invoiceService.deleteById(id);
    }}
