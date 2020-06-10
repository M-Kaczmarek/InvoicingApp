package pl.mariuszkaczmarek.invoicingapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mariuszkaczmarek.invoicingapp.model.Company;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
import pl.mariuszkaczmarek.invoicingapp.service.InvoiceService;
import pl.mariuszkaczmarek.invoicingapp.service.TransportCompanyService;

import java.net.URI;
import java.util.ArrayList;
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
    @GetMapping
    public ResponseEntity<List<TransportCompany>> findAll(){
        logger.info("GET /api/company -> findall start");
        logger.error("dasdas");
        List<TransportCompany> companyIterator =  transportCompanyService.findAll();
        logger.info("GET /api/company -> findall end");
         return ResponseEntity.ok(companyIterator);
    }

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

    @PostMapping
    public ResponseEntity<Company> saveTransportCompany(@RequestBody TransportCompany company){
        TransportCompany transportCompany = transportCompanyService.addCompany(company);
      return ResponseEntity.created(URI.create("/"+transportCompany.getId())).body(transportCompany);
    }
    @PutMapping
    public ResponseEntity<Company> updateTransportCompany(@RequestBody TransportCompany company){
        TransportCompany transportCompany = transportCompanyService.updateCompany(company);
       return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        transportCompanyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
