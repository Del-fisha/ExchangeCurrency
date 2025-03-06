package pet.exchangecurrency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.exchangecurrency.model.ExchangeRate;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
}