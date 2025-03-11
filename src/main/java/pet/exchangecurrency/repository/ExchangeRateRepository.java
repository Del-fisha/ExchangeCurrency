package pet.exchangecurrency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pet.exchangecurrency.model.ExchangeRate;

import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    @Query("SELECT e FROM ExchangeRate e WHERE e.baseCurrency.code LIKE %:baseCurrencyCode%")
    List<ExchangeRate> findAllByBaseCurrencyCodeLike(@Param("baseCurrencyCode") String baseCurrencyCode);
}