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
    private String baseCurrencyCode;
    private String targetCurrencyCode;
    private double rate;

    public ExchangeRateDto(String baseCurrencyCode, String targetCurrencyCode, double rate) {
        this.baseCurrencyCode = baseCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
        this.rate = rate;
    }

    public ExchangeRateDto(Long id, String baseCurrencyCode, String targetCurrencyCode, double rate) {
        this(baseCurrencyCode, targetCurrencyCode, rate);
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("id=%d, baseCurrencyCode=%s, targetCurrencyCode=%s, Rate=%f",
                id, baseCurrencyCode, targetCurrencyCode, rate);
    }
}