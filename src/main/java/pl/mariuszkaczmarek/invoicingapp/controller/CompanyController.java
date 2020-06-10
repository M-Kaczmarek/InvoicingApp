package pl.mariuszkaczmarek.invoicingapp.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mariuszkaczmarek.invoicingapp.model.Company;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
import pl.mariuszkaczmarek.invoicingapp.service.TransportCompanyService;
import pl.mariuszkaczmarek.invoicingapp.service.InvoiceService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    Logger logger = LoggerFactory.getLogger(CompanyController.class);
    private TransportCompanyService transportCompanyService;


    public CompanyController(TransportCompanyService transportCompanyService) {
        this.transportCompanyService = transportCompanyService;

    }
    @ApiOperation(value = "Find All companies",notes = "Give companies with invoices ",httpMethod = "GET")
    @GetMapping
    public ResponseEntity<List<TransportCompany>> findAll(){
        logger.info("GET /api/company -> findall start");
        List<TransportCompany> companyIterator =  transportCompanyService.findAll();
        logger.info("GET /api/company -> findall end");
        return ResponseEntity.ok(companyIterator);
    }
    @ApiOperation(value = "Find company by id", notes = "Give the company with invoices", httpMethod = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TransportCompany>> findById(@PathVariable Long id){
        logger.info("Transport company start working");
        Optional<TransportCompany> company =transportCompanyService.findById(id);
        if(company.isEmpty()){
            logger.info("Transport company does not exist with given id="+id);
            return ResponseEntity.notFound().build();
        }
        logger.info("Transport company end working");
        return ResponseEntity.ok(company);
    }
    @ApiOperation(value = "Save the company", notes = "Save the company with invoices to database", httpMethod = "POST")
    @PostMapping
    public ResponseEntity<Company> saveTransportCompany(@RequestBody TransportCompany company){
        TransportCompany transportCompany = transportCompanyService.addCompany(company);
        return ResponseEntity.created(URI.create("/"+transportCompany.getId())).body(transportCompany);
    }
    @ApiOperation(value = "Update the company", notes = "Save the company with new invoice or correct later invoices", httpMethod = "PUT")
    @PutMapping
    public ResponseEntity<Company> updateTransportCompany(@RequestBody TransportCompany company){
        TransportCompany transportCompany = transportCompanyService.updateCompany(company);
        return ResponseEntity.noContent().build();
    }
    @ApiOperation(value = "Delete the company by id", notes = "Delete the company from database with invoices by id company", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        transportCompanyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

