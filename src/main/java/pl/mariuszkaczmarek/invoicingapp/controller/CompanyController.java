package pl.mariuszkaczmarek.invoicingapp.controller;

import org.springframework.web.bind.annotation.*;
import pl.mariuszkaczmarek.invoicingapp.model.Company;
import pl.mariuszkaczmarek.invoicingapp.model.Invoice;
import pl.mariuszkaczmarek.invoicingapp.service.CompanyService;
import pl.mariuszkaczmarek.invoicingapp.service.InvoiceService;

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
    public Iterable<Company> findAll(){
        return companyService.findAll();
    }
    @PostMapping
    public Company save(@RequestBody Company company){
        return companyService.addCompany(company);
    }

}
