package pet.exchangecurrency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.exchangecurrency.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findByCode(String code);
}