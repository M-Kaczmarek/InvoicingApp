package pl.mariuszkaczmarek.invoicingapp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
import pl.mariuszkaczmarek.invoicingapp.repostiory.TransportCompanyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransportCompanyServiceTest {

    @Test
    @DisplayName("Should find all company and show company")
    void should_show_all_added_transportCompany() {
        //given
        var transportCompanyRepository = mock(TransportCompanyRepository.class);
        var transportCompanyService = new TransportCompanyService(transportCompanyRepository);
        List<TransportCompany> companyList = new ArrayList<>();
        var transportCompany1 = new TransportCompany();
        var transportCompany2 = new TransportCompany();
        companyList.add(transportCompany1);
        companyList.add(transportCompany2);
        when(transportCompanyRepository.findAll()).thenReturn(companyList);

        //when
        var transportCompanyList = transportCompanyService.findAll();

        //then
        assertThat(transportCompanyList).hasSize(companyList.size());
    }

    @Test
    @DisplayName("Should find company by id and show this company")
    void should_show_company_by_id(){
        //given
        var transportCompanyRepository = mock(TransportCompanyRepository.class);
        var transportCompanyService = new TransportCompanyService(transportCompanyRepository);
        List<TransportCompany> companyList = new ArrayList<>();
        var transportCompany1 = new TransportCompany();
        var transportCompany2 = new TransportCompany();
        companyList.add(transportCompany1);
        companyList.add(transportCompany2);
        when(transportCompanyService.findById(1L)).thenReturn(Optional.of(companyList.get(1)));

        //when
        Optional<TransportCompany> company = transportCompanyService.findById(1L);

        //then
        assertThat(company.get()).isEqualTo(companyList.get(1));
    }
    @Test
    @DisplayName("Should save company and return this company")
    void should_save_company() {
        //given
        var transportCompanyRepository = mock(TransportCompanyRepository.class);
        var transportCompanyService = new TransportCompanyService(transportCompanyRepository);
        var transportCompany1 = new TransportCompany();
        when(transportCompanyRepository.save(any(TransportCompany.class))).thenReturn(transportCompany1);

        //when
        TransportCompany transportCompany = transportCompanyService.addCompany(transportCompany1);

        //then
        assertThat(transportCompany).isNotNull();
        assertThat(transportCompany).isEqualTo(transportCompany1);


    }
}
