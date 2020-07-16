package pl.mariuszkaczmarek.invoicingapp.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mariuszkaczmarek.invoicingapp.model.Invoice;
import pl.mariuszkaczmarek.invoicingapp.service.InvoiceService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @ApiOperation(value = "Find All invoices", notes = "provide all invoices from database", httpMethod = "GET")
    @GetMapping
    public ResponseEntity<List<Invoice>> findAll() {
        List<Invoice> invoices = invoiceService.findAll();

        return ResponseEntity.ok(invoices);
    }

    @ApiOperation(value = "Find invoice by id", notes = "Give the invoice from database by id", httpMethod = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> findById(@PathVariable Long id) {
        Invoice invoice = invoiceService.findById(id);

        return ResponseEntity.ok(invoice);
    }

    @ApiOperation(value = "Save the invoice", notes = "Save the invoice to database", httpMethod = "POST")
    @PostMapping
    public ResponseEntity<Invoice> save(@RequestBody Invoice invoice) {
        Invoice result = invoiceService.addInvoice(invoice);

        return ResponseEntity.created(URI.create("/" + result.getId())).body(invoice);
    }

    @ApiOperation(value = "Update the invoice", notes = "Correct invoice", httpMethod = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice, @PathVariable Long id) {
        invoiceService.updateInvoice(invoice, id);

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete the invoice by id", notes = "Delete the invoice from database by id", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        invoiceService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
