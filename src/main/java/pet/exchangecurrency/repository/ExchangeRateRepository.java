package pet.exchangecurrency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pet.exchangecurrency.dto.ExchangeConvertedDto;
import pet.exchangecurrency.model.ExchangeRate;

import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    @Query("SELECT e FROM ExchangeRate e WHERE e.baseCurrency.code LIKE %:baseCurrencyCode%")
    List<ExchangeRate> findAllByBaseCurrencyCodeLike(@Param("baseCurrencyCode") String baseCurrencyCode);

    @Query("SELECT e FROM ExchangeRate e WHERE e.baseCurrency.code LIKE %:baseCurrencyCode% AND e.targetCurrency.code = :targetCurrencyCode")
    ExchangeRate findByBaseCurrencyCodeLikeAndTargetCurrencyCode(
            @Param("baseCurrencyCode") String baseCurrencyCode,
            @Param("targetCurrencyCode") String targetCurrencyCode);

    // Todo Проблема в добавлении новой пары (Если она есть в базе, то всё равно создаётся новая)

    @Query("SELECT e FROM ExchangeRate e WHERE e.baseCurrency.code LIKE %:baseCurrencyCode% AND e.targetCurrency.code = :targetCurrencyCode")
    ExchangeConvertedDto findByBaseCurrencyCodeAndTargetCurrencyCode(
            @Param("baseCurrencyCode") String baseCurrencyCode,
            @Param("targetCurrencyCode") String targetCurrencyCode);
}