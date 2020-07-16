package pl.mariuszkaczmarek.invoicingapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mariuszkaczmarek.invoicingapp.model.Invoice;
import pl.mariuszkaczmarek.invoicingapp.repostiory.InvoiceRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class InvoiceServiceTest {

    private InvoiceRepository invoiceRepository;

    private InvoiceService invoiceService;

    private Invoice invoice;

    @BeforeEach
    void setup(){
        invoiceRepository = mock(InvoiceRepository.class);
        invoiceService = new InvoiceService(invoiceRepository);
        invoice = new Invoice();
        invoice.setName("ExampleName");
        invoice.setSurrName("ExampleSurrName");
    }

    @Test
    void findById_should_find_invoice(){
        //given
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));

        //when
        var foundInvoice = invoiceService.findById(3L);

        //then
        assertThat(foundInvoice).isEqualTo(invoice);
    }

    @Test
    void findById_should_throw_NotFoundInvoiceException(){
        //given

        //when
        var exception = catchThrowable(()-> invoiceService.findById(3L));

        //then
        assertThat(exception.getMessage()).isEqualTo("Invoice not found");
    }

    @Test
    void addInvoice_should_save_Invoice(){
        //given
        when(invoiceRepository.save(any())).thenReturn(invoice);

        //when
        var addedInvoice = invoiceService.addInvoice(invoice);

        //then
        assertThat(addedInvoice).isEqualTo(invoice);
    }

    @Test
    void  addInvoice_should_throw_ExistInvoiceException(){
        //given
        when(invoiceRepository.existsByNameAndSurrName(invoice.getName(),invoice.getSurrName())).thenReturn(true);

        //when
        var exception = catchThrowable(()-> invoiceService.addInvoice(invoice));

        //then
        assertThat(exception.getMessage()).isEqualTo("Invoice already exist");
    }

    @Test
    void updateInvoice_should_update_Invoice(){
        //given
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));
        when(invoiceRepository.save(any())).thenReturn(invoice);

        //when
        var updatedInvoice = invoiceService.updateInvoice(invoice,3L);

        //then
        assertThat(updatedInvoice).isEqualTo(invoice);
    }

    @Test
    void updateInvoice_should_throw_NotFoundInvoiceException(){
        //given

        //when
        var exception = catchThrowable(()-> invoiceService.updateInvoice(invoice, 3L));

        //then
        assertThat(exception.getMessage()).isEqualTo("Invoice not found");
    }

    @Test
    void deleteById_should_delete_invoice(){
        //given
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));

        //when
        invoiceService.deleteById(4L);

        //then
        verify(invoiceRepository, times(1)).deleteById(4L);
    }

    @Test
    void deleteById_should_throw_NotFoundInvoiceException(){
        //given

        //when
        var exception = catchThrowable(()-> invoiceService.deleteById(3L));

        //then
        assertThat(exception.getMessage()).isEqualTo("Invoice not found");
    }

}
