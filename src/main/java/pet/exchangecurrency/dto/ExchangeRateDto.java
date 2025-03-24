package pet.exchangecurrency.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link pet.exchangecurrency.model.ExchangeRate}
 */
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRateDto implements Serializable {
    private Long id;
    CurrencyDto baseCurrency;
    CurrencyDto targetCurrency;
    double rate;

    public ExchangeRateDto(CurrencyDto baseCurrency, CurrencyDto targetCurrency, double rate) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }

    public ExchangeRateDto(Long id, CurrencyDto baseCurrency, CurrencyDto targetCurrency, double rate) {
        this(baseCurrency, targetCurrency, rate);
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("id=%d, baseCurrencyCode=%s, targetCurrencyCode=%s, Rate=%f",
                id, baseCurrency, targetCurrency, rate);
    }
}