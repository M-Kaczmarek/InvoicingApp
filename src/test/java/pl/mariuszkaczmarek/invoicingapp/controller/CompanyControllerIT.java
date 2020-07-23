package pl.mariuszkaczmarek.invoicingapp.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mariuszkaczmarek.invoicingapp.exception.ExistCompanyException;
import pl.mariuszkaczmarek.invoicingapp.exception.NotFoundCompanyException;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;
import pl.mariuszkaczmarek.invoicingapp.repostiory.TransportCompanyRepository;
import pl.mariuszkaczmarek.invoicingapp.service.TransportCompanyService;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompanyControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String PATH = "http://localhost:%d/api/company";

    private String url;

    @Autowired
    private TransportCompanyRepository transportCompanyRepository;

    private TransportCompanyService transportCompanyService;

    private TransportCompany transportCompany;

    @BeforeEach
    void setup() {
        url = String.format(PATH, port);
        transportCompanyService = new TransportCompanyService(transportCompanyRepository);
        transportCompany = new TransportCompany();
        transportCompany.setName("ExampleCompany");
        transportCompany = transportCompanyRepository.save(transportCompany);
    }

    @AfterEach
    void tearDown() {
        transportCompanyRepository.deleteAllInBatch();
    }

    @Test
    void getAll_should_return_status_ok() {
        //given
        var size = this.transportCompanyRepository.findAll().size();

        //when
        var result = testRestTemplate.getForEntity(url, TransportCompany[].class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().length).isEqualTo(size);
    }

    @Test
    void getById_should_return_status_ok() {
        //given
        url += ("/" + transportCompany.getId());

        //when
        var result = testRestTemplate.getForEntity(url, TransportCompany.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(transportCompany);
    }

    @Test
    void getById_should_result_status_NotFound() {
        //given
        url += ("/" + 234);

        //when
        var result = testRestTemplate.getForEntity(url, NotFoundCompanyException.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody().getMessage()).isEqualTo("Company not found");
    }

    @Test
    void addCompany_should_result_created() {
        //given
        TransportCompany newTransportCompany = new TransportCompany();

        //when
        var result = testRestTemplate.postForEntity(url, newTransportCompany, TransportCompany.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isEqualTo(newTransportCompany);
    }

    @Test
    void addCompany_should_result_Conflict() {
        //given

        //when
        var result = testRestTemplate.postForEntity(url, transportCompany, ExistCompanyException.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(result.getBody().getMessage()).isEqualTo("Company already exists");
    }

    @Test
    void updateCompany_should_result_noContent() {
        //given
        url += ("/" + transportCompany.getId());
        transportCompany.setName("newName");

        //when
        var result = testRestTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(transportCompany, new HttpHeaders()), TransportCompany.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(transportCompany).isEqualTo(transportCompanyRepository.findById(transportCompany.getId()).get());
    }

    @Test
    void updateCompany_should_result_noFound() {
        //given
        url += ("/" + 234);

        //when
        var result = testRestTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(transportCompany, new HttpHeaders()), NotFoundCompanyException.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody().getMessage()).isEqualTo("Company not found");
    }

    @Test
    void deleteById_should_result_noContent() {
        //given
        url += ("/" + transportCompany.getId());

        //when
        var result = testRestTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(null, new HttpHeaders()), TransportCompany.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(transportCompanyRepository.existsByName(transportCompany.getName())).isEqualTo(false);
    }

    @Test
    void deleteById_should_result_noFound() {
        //given
        url += ("/" + 234);

        //when
        var result = testRestTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(null, new HttpHeaders()), NotFoundCompanyException.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody().getMessage()).isEqualTo("Company not found");
    }

}
