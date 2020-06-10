package pl.mariuszkaczmarek.invoicingapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mariuszkaczmarek.invoicingapp.model.Invoice;
import pl.mariuszkaczmarek.invoicingapp.service.TransportCompanyService;
import pl.mariuszkaczmarek.invoicingapp.service.InvoiceService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    Logger logger = LoggerFactory.getLogger(InvoiceController.class);
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {

        this.invoiceService = invoiceService;
    }
    @GetMapping
    public ResponseEntity<List<Invoice>> findAll(){
        List<Invoice> invoices = invoiceService.findAll();
        return ResponseEntity.ok(invoices);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Invoice>> findById(@RequestParam Long id){
        Optional<Invoice> invoice = invoiceService.findById(id);
        if(invoice.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<Invoice> save(@RequestBody Invoice invoice){
        Invoice result =invoiceService.addInvoice(invoice);
        return ResponseEntity.created(URI.create("/"+result.getId())).build();
    }

    @PutMapping
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice){
        invoiceService.updateInvoice(invoice);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam Long id){
        invoiceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }}
