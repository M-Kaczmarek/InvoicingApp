package pl.mariuszkaczmarek.invoicingapp.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mariuszkaczmarek.invoicingapp.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
