package pl.mariuszkaczmarek.invoicingapp.service;

import org.springframework.stereotype.Service;
import pl.mariuszkaczmarek.invoicingapp.model.Company;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
import pl.mariuszkaczmarek.invoicingapp.repostiory.TransportRepository;

import java.util.List;

@Service
public class CompanyService {

    private TransportRepository transportRepository;

    public CompanyService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }
    public TransportCompany addCompany(Company company){
//        Set<Invoice> invoices = company.getInvoices();
//        invoices.stream()
//            .forEach(i -> invoiceRepository.save(i));

        return transportRepository.save((TransportCompany)company);
    }
    public List<TransportCompany> findAll(){
        return transportRepository.findAll();
    }
}
