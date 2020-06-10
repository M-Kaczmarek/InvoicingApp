package pl.mariuszkaczmarek.invoicingapp.controller;

import org.springframework.web.bind.annotation.*;
import pl.mariuszkaczmarek.invoicingapp.model.Company;
import pl.mariuszkaczmarek.invoicingapp.model.Invoice;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
import pl.mariuszkaczmarek.invoicingapp.service.CompanyService;
import pl.mariuszkaczmarek.invoicingapp.service.InvoiceService;

import java.util.Optional;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    private CompanyService companyService;
    private InvoiceService invoiceService;

    public InvoiceController(CompanyService companyService, InvoiceService invoiceService) {
        this.companyService = companyService;
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
