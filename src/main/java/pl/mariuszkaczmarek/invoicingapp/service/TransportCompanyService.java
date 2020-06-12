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

    public TransportCompany addCompany(TransportCompany company){
        return transportCompanyRepository.save(company);
    }
    public List<TransportCompany> findAll(){
        return transportCompanyRepository.findAll();
    }

    public Optional<TransportCompany> findById(Long id){
        return transportCompanyRepository.findById(id);
    }

    public TransportCompany updateCompany(Company company, Long id){
        if(transportCompanyRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("Can not find this company");
        }
        return transportCompanyRepository.save((TransportCompany)company);
    }

    public void deleteById(Long id){
        transportCompanyRepository.deleteById(id);
    }
}
