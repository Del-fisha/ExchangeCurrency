package pet.exchangecurrency.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExchangeConvertedDto extends ExchangeRateDto {
    double amount;
    double convertedAmount;

    public ExchangeConvertedDto(ExchangeRateDto rate) {
        super(rate.baseCurrencyCode, rate.targetCurrencyCode, rate.rate);
    }

    public ExchangeConvertedDto(ExchangeRateDto rate, double amount) {
        this(rate);
        this.amount = amount;
        this.convertedAmount = amount * this.getRate();
        // ToDo - Насколько грамотно вычислять convertedAmount здесь, а не в сервисе?
    }

    @Override
    public String toString() {
        return String.format("id=%d, baseCurrencyCode=%s, targetCurrencyCode=%s, Rate=%f, Amount=%f, ConvertedAmount=%f",
                id, baseCurrencyCode, targetCurrencyCode, rate, amount, convertedAmount);
    }
}
