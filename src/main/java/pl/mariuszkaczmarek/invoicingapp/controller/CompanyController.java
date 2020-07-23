package pl.mariuszkaczmarek.invoicingapp.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mariuszkaczmarek.invoicingapp.model.Company;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
import pl.mariuszkaczmarek.invoicingapp.service.TransportCompanyService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private TransportCompanyService transportCompanyService;

    public CompanyController(TransportCompanyService transportCompanyService) {
        this.transportCompanyService = transportCompanyService;
    }

    @ApiOperation(value = "Find All companies", notes = "Give companies with invoices ", httpMethod = "GET")
    @GetMapping
    public ResponseEntity<List<TransportCompany>> findAll() {
        List<TransportCompany> companyIterator = transportCompanyService.findAll();

        return ResponseEntity.ok(companyIterator);
    }

    @ApiOperation(value = "Find company by id", notes = "Give the company with invoices", httpMethod = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<TransportCompany> findById(@PathVariable Long id) {
        TransportCompany company = transportCompanyService.findById(id);

        return ResponseEntity.ok(company);
    }

    @ApiOperation(value = "Save the company", notes = "Save the company with invoices to database", httpMethod = "POST")
    @PostMapping
    public ResponseEntity<TransportCompany> saveTransportCompany(@RequestBody TransportCompany company) {
        TransportCompany transportCompany = transportCompanyService.addCompany(company);

        return ResponseEntity.created(URI.create("/" + transportCompany.getId())).body(transportCompany);
    }

    @ApiOperation(value = "Update the company", notes = "Save the company with new invoice or correct later invoices", httpMethod = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTransportCompany(@RequestBody TransportCompany company, @PathVariable Long id) {
        transportCompanyService.updateCompany(company, id);

        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Delete the company by id", notes = "Delete the company from database with invoices by id company", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        transportCompanyService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

