package pl.mariuszkaczmarek.invoicingapp.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mariuszkaczmarek.invoicingapp.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
