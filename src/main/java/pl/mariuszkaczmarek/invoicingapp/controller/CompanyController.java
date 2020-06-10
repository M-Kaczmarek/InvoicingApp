package pl.mariuszkaczmarek.invoicingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import pl.mariuszkaczmarek.invoicingapp.model.Company;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
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
    public Iterable<?> findAll(){
        return companyService.findAll();
    }
    @PostMapping
    public Company saveTransportCompany(@RequestBody TransportCompany company){

        return companyService.addCompany(company);
    }
//    @PostMapping
//    public Company saveItCompany(@RequestBody Com company){
//        return companyService.addCompany(company);
//    }

}
