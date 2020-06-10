package pl.mariuszkaczmarek.invoicingapp.controller;

import org.springframework.web.bind.annotation.*;
import pl.mariuszkaczmarek.invoicingapp.model.Company;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
import pl.mariuszkaczmarek.invoicingapp.service.CompanyService;
import pl.mariuszkaczmarek.invoicingapp.service.InvoiceService;

import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private CompanyService companyService;
    private InvoiceService invoiceService;

    public CompanyController(CompanyService companyService, InvoiceService invoiceService) {
        this.companyService = companyService;
        this.invoiceService = invoiceService;
    }
    @GetMapping
    public Iterable<?> findAll(){
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<TransportCompany> findById(@RequestParam Long id){
        return companyService.findById(id);
    }

    @PostMapping
    public Company saveTransportCompany(@RequestBody TransportCompany company){
        return companyService.addCompany(company);
    }
    @PutMapping
    public Company updateTransportCompany(@RequestBody TransportCompany company){
        return companyService.updateCompany(company);
    }
    @DeleteMapping
    public void deleteById(@RequestParam Long id){
        companyService.deleteById(id);
    }



}
