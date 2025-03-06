package pet.exchangecurrency.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link pet.exchangecurrency.model.ExchangeRate}
 */
@Value
public class ExchangeRateDto implements Serializable {
    Long id;
    CurrencyDto baseCurrency;
    CurrencyDto targetCurrency;
    long Rate;
}