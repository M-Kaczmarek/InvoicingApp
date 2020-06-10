package pl.mariuszkaczmarek.invoicingapp.controller;

import org.springframework.web.bind.annotation.*;
import pl.mariuszkaczmarek.invoicingapp.model.Company;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
import pl.mariuszkaczmarek.invoicingapp.service.TransportCompanyService;
import pl.mariuszkaczmarek.invoicingapp.service.InvoiceService;

import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private TransportCompanyService transportCompanyService;
    private InvoiceService invoiceService;

    public CompanyController(TransportCompanyService transportCompanyService, InvoiceService invoiceService) {
        this.transportCompanyService = transportCompanyService;
        this.invoiceService = invoiceService;
    }
    @GetMapping
    public Iterable<?> findAll(){
        return transportCompanyService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TransportCompany> findById(@RequestParam Long id){
        return transportCompanyService.findById(id);
    }

    @PostMapping
    public Company saveTransportCompany(@RequestBody TransportCompany company){
        return transportCompanyService.addCompany(company);
    }
    @PutMapping
    public Company updateTransportCompany(@RequestBody TransportCompany company){
        return transportCompanyService.updateCompany(company);
    }
    @DeleteMapping
    public void deleteById(@RequestParam Long id){
        transportCompanyService.deleteById(id);
    }



}
