package pl.mariuszkaczmarek.invoicingapp.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.mariuszkaczmarek.invoicingapp.exception.ExistInvoiceException;
import pl.mariuszkaczmarek.invoicingapp.exception.NotFoundInvoiceException;
import pl.mariuszkaczmarek.invoicingapp.model.Invoice;
import pl.mariuszkaczmarek.invoicingapp.repostiory.InvoiceRepository;
import pl.mariuszkaczmarek.invoicingapp.service.InvoiceService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InvoiceControllerTestIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String PATH = "http://localhost:%d/api/invoice";

    private String url;

    @Autowired
    private InvoiceRepository invoiceRepository;

    private InvoiceService invoiceService;

    private Invoice invoice;

    @BeforeEach
    void setup() {
        url = String.format(PATH, port);
        invoiceService = new InvoiceService(invoiceRepository);
        invoice = new Invoice();
        invoice.setName("ExampleName");
        invoice.setSurrName("ExampleSurrName");
        invoice = invoiceRepository.save(invoice);
    }

    @AfterEach
    void tearDown() {
        invoiceRepository.deleteAllInBatch();
    }

    @Test
    void getAll_should_return_status_ok() {
        //given
        var size = invoiceRepository.findAll().size();

        //when
        var result = testRestTemplate.getForEntity(url, Invoice[].class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().length).isEqualTo(size);
    }

    @Test
    void getById_should_return_status_ok() {
        //given
        url += ("/" + invoice.getId());

        //when
        var result = testRestTemplate.getForEntity(url, Invoice.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(invoice);
    }

    @Test
    void getById_should_result_status_NotFound() {
        //given
        url += ("/" + 123);

        //when
        var result = testRestTemplate.getForEntity(url, NotFoundInvoiceException.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody().getMessage()).isEqualTo("Invoice not found");
    }

    @Test
    void addCompany_should_result_created() {
        //given
        Invoice newInvoice = new Invoice();

        //when
        var result = testRestTemplate.postForEntity(url, newInvoice, Invoice.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isEqualTo(newInvoice);
    }

    @Test
    void addCompany_should_result_Conflict() {
        //given

        //when
        var result = testRestTemplate.postForEntity(url, invoice, ExistInvoiceException.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(result.getBody().getMessage()).isEqualTo("Invoice already exist");
    }

    @Test
    void updateCompany_should_result_noContent() {
        //given
        url += ("/" + invoice.getId());
        invoice.setName("newName");
        invoice.setSurrName("newSurrName");

        //when
        var result = testRestTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(invoice, new HttpHeaders()), Invoice.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(invoice).isEqualTo(invoiceRepository.findById(invoice.getId()).get());
    }

    @Test
    void updateCompany_should_result_noFound() {
        //given
        url += ("/" + 123);
        //when
        var result = testRestTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(invoice, new HttpHeaders()), NotFoundInvoiceException.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody().getMessage()).isEqualTo("Invoice not found");
    }

    @Test
    void deleteById_should_result_noContent() {
        //given
        url += ("/" + invoice.getId());

        //when
        var result = testRestTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(null, new HttpHeaders()), Invoice.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(invoiceRepository.existsByNameAndSurrName(invoice.getName(), invoice.getSurrName())).isEqualTo(false);
    }

    @Test
    void deleteById_should_result_noFound() {
        //given
        url += ("/" + 123);

        //when
        var result = testRestTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(null, new HttpHeaders()), NotFoundInvoiceException.class);

        //then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody().getMessage()).isEqualTo("Invoice not found");
    }


}
