package pl.mariuszkaczmarek.invoicingapp.service;

import org.springframework.stereotype.Service;
import pl.mariuszkaczmarek.invoicingapp.model.Company;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
import pl.mariuszkaczmarek.invoicingapp.repostiory.TransportCompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransportCompanyService {

    private TransportCompanyRepository transportCompanyRepository;

    public TransportCompanyService(TransportCompanyRepository transportCompanyRepository) {
        this.transportCompanyRepository = transportCompanyRepository;
    }

    public TransportCompany addCompany(Company company){
//        Set<Invoice> invoices = company.getInvoices();
//        invoices.stream()
//            .forEach(i -> invoiceRepository.save(i));

        return transportCompanyRepository.save((TransportCompany)company);
    }
    public List<TransportCompany> findAll(){
        return transportCompanyRepository.findAll();
    }

    public Optional<TransportCompany> findById(Long id){
        return transportCompanyRepository.findById(id);
    }

    public TransportCompany updateCompany(Company company){
        return transportCompanyRepository.save((TransportCompany)company);
    }

    public void deleteById(Long id){
        transportCompanyRepository.deleteById(id);
    }
}
