package pl.mariuszkaczmarek.invoicingapp.service;

import org.springframework.stereotype.Service;
import pl.mariuszkaczmarek.invoicingapp.model.Company;
import pl.mariuszkaczmarek.invoicingapp.repostiory.CompanyRepository;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    public Company addCompany(Company company){
//        Set<Invoice> invoices = company.getInvoices();
//        invoices.stream()
//            .forEach(i -> invoiceRepository.save(i));
        return companyRepository.save(company);
    }
    public Iterable<Company> findAll(){
        return companyRepository.findAll();
    }
}
