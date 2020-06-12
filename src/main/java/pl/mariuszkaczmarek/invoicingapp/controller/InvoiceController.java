package pl.mariuszkaczmarek.invoicingapp.controller;

import io.swagger.annotations.ApiOperation;
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
    private Logger logger = LoggerFactory.getLogger(InvoiceController.class);
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @ApiOperation(value = "Find All invoices", notes = "provide all invoices from database", httpMethod = "GET")
    @GetMapping
    public ResponseEntity<List<Invoice>> findAll() {
        logger.info("Starting find all invoices");
        List<Invoice> invoices = invoiceService.findAll();
        logger.info("Found all invoices");
        return ResponseEntity.ok(invoices);
    }

    @ApiOperation(value = "Find invoice by id", notes = "Give the invoice from database by id", httpMethod = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Invoice>> findById(@RequestParam Long id) {
        logger.info("Starting find Invoice with id=" + id);
        Optional<Invoice> invoice = invoiceService.findById(id);
        if (invoice.isEmpty()) {
            logger.info("Invoice does not exist with given id=" + id);
            return ResponseEntity.notFound().build();
        }
        logger.info("Found Invoice with id=" + id);
        return ResponseEntity.ok(invoice);
    }

    @ApiOperation(value = "Save the invoice", notes = "Save the invoice to database", httpMethod = "POST")
    @PostMapping
    public ResponseEntity<Invoice> save(@RequestBody Invoice invoice) {
        logger.info("Starting save Invoice to database");
        Invoice result = invoiceService.addInvoice(invoice);
        logger.info("Saved Invoice with id=" + result.getId());
        return ResponseEntity.created(URI.create("/" + result.getId())).build();
    }

    @ApiOperation(value = "Update the invoice", notes = "Correct invoice", httpMethod = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice,@PathVariable Long id) {
        logger.info("Starting update Invoice");
        try {
           invoiceService.updateInvoice(invoice, id);
        }catch (IllegalArgumentException ex){
            logger.info("Invoice does not exist with given id=" + id);
            return ResponseEntity.notFound().build();
        }
        logger.info("Updated Invoice with id=" + id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete the invoice by id", notes = "Delete the invoice from database by id", httpMethod = "DELETE")
    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam Long id) {
        logger.info("Starting delete Invoice");
        invoiceService.deleteById(id);
        logger.info("Deleted Invoice with id=" + id);
        return ResponseEntity.noContent().build();
    }
}
