package pl.mariuszkaczmarek.invoicingapp.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mariuszkaczmarek.invoicingapp.model.TransportCompany;

@Repository
public interface TransportCompanyRepository extends JpaRepository<TransportCompany, Long> {
}
