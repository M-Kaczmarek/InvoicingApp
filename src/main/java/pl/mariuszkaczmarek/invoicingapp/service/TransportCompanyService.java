package pl.mariuszkaczmarek.invoicingapp.service;

import org.springframework.stereotype.Service;
import pl.mariuszkaczmarek.invoicingapp.exception.ExistCompanyException;
import pl.mariuszkaczmarek.invoicingapp.exception.NotFoundCompanyException;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
import pl.mariuszkaczmarek.invoicingapp.repostiory.TransportCompanyRepository;

import java.util.List;

@Service
public class TransportCompanyService {

    private TransportCompanyRepository transportCompanyRepository;

    public TransportCompanyService(TransportCompanyRepository transportCompanyRepository) {
        this.transportCompanyRepository = transportCompanyRepository;
    }

    public TransportCompany addCompany(TransportCompany company) {
        if (transportCompanyRepository.existsByName(company.getName())) {
            throw new ExistCompanyException("Company already exists");
        }

        return transportCompanyRepository.save(company);
    }

    public List<TransportCompany> findAll() {
        return transportCompanyRepository.findAll();
    }

    public TransportCompany findById(Long id) {
        return transportCompanyRepository.findById(id).orElseThrow(() -> new NotFoundCompanyException("Company not found"));
    }

    public TransportCompany updateCompany(TransportCompany company, Long id) {
        transportCompanyRepository.findById(id).orElseThrow(() -> new NotFoundCompanyException("Company not found"));
        company.setId(id);

        return transportCompanyRepository.save(company);
    }

    public void deleteById(Long id) {
        transportCompanyRepository.findById(id).orElseThrow(() -> new NotFoundCompanyException("Company not found"));
        transportCompanyRepository.deleteById(id);
    }
}
