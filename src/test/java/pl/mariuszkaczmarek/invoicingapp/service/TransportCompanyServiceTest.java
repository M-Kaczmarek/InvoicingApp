package pl.mariuszkaczmarek.invoicingapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
import pl.mariuszkaczmarek.invoicingapp.repostiory.TransportCompanyRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TransportCompanyServiceTest {

    private TransportCompanyRepository transportCompanyRepository;

    private TransportCompanyService transportCompanyService;

    private TransportCompany transportCompany1;

    @BeforeEach
    void setup() {
        transportCompanyRepository = mock(TransportCompanyRepository.class);
        transportCompanyService = new TransportCompanyService(transportCompanyRepository);
        transportCompany1 = new TransportCompany();
        transportCompany1.setName("ExampleCompany");
    }

    @Test
    void findById_should_find_company() {
        //given
        when(transportCompanyRepository.findById(1L)).thenReturn(Optional.of(transportCompany1));

        //when
        TransportCompany company = transportCompanyService.findById(1L);

        //then
        assertThat(company).isEqualTo(transportCompany1);
    }

    @Test
    void findById_should_throw_NotFoundCompanyException() {
        //given

        //when
        var exception = catchThrowable(() -> transportCompanyService.findById(2L));

        //then
        assertThat(exception.getMessage()).isEqualTo("Company not found");
    }

    @Test
    void addCompany_should_add_company() {
        //given
        when(transportCompanyRepository.save(any(TransportCompany.class))).thenReturn(transportCompany1);

        //when
        var transportCompany = transportCompanyService.addCompany(transportCompany1);

        //then
        assertThat(transportCompany).isNotNull();
        assertThat(transportCompany).isEqualTo(transportCompany1);

    }

    @Test
    void addCompany_should_throw_ExistCompanyException() {
        //when
        when(transportCompanyRepository.existsByName(anyString())).thenReturn(true);

        //when
        var exception = catchThrowable(() -> transportCompanyService.addCompany(transportCompany1));

        //then
        assertThat(exception.getMessage()).isEqualTo("Company already exists");
    }

    @Test
    void updateCompany_should_update_company() {
        //given
        when(transportCompanyRepository.findById(anyLong())).thenReturn(Optional.of(transportCompany1));
        when(transportCompanyRepository.save(any())).thenReturn(transportCompany1);

        //when
        var updatedCompany = transportCompanyService.updateCompany(transportCompany1, 3L);

        //then
        assertThat(updatedCompany).isEqualTo(transportCompany1);
    }

    @Test
    void updateCompany_should_throw_NotFoundCompanyException() {
        //given

        //when
        var exception = catchThrowable(() -> transportCompanyService.updateCompany(transportCompany1, 2L));

        //then
        assertThat(exception.getMessage()).isEqualTo("Company not found");
    }

    @Test
    void deleteById_should_delete_company() {
        //given
        when(transportCompanyRepository.findById(anyLong())).thenReturn(Optional.of(transportCompany1));

        //given
        transportCompanyService.deleteById(3L);

        //when
        verify(transportCompanyRepository, times(1)).deleteById(3L);
    }

    @Test
    void deleteById_should_throw_NotFoundCompanyException() {
        //given

        //when
        var exception = catchThrowable(() -> transportCompanyService.deleteById(1L));

        //then
        assertThat(exception.getMessage()).isEqualTo("Company not found");
    }


}
