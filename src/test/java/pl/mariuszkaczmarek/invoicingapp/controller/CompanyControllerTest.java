package pl.mariuszkaczmarek.invoicingapp.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
import pl.mariuszkaczmarek.invoicingapp.service.TransportCompanyService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransportCompanyService transportCompanyService;

    @Test
    void test() throws Exception {
        TransportCompany transportCompany = new TransportCompany();
        List<TransportCompany> transportCompanyList = Arrays.asList(transportCompany);

        when(transportCompanyService.findAll()).thenReturn(transportCompanyList);

        mockMvc.perform(get("/api/company")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}

