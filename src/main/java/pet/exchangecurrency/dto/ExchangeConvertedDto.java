package pet.exchangecurrency.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExchangeConvertedDto extends ExchangeRateDto {

    @JsonIgnore
    private Long id;
    double amount;
    double convertedAmount;

    public ExchangeConvertedDto(ExchangeRateDto rate) {
        super(rate.baseCurrency, rate.targetCurrency, rate.rate);
    }

    public ExchangeConvertedDto(ExchangeRateDto rate, double amount) {
        this(rate);
        this.amount = amount;
        this.convertedAmount = amount * this.getRate();
        // ToDo - Насколько грамотно вычислять convertedAmount здесь, а не в сервисе?
    }

    @Override
    public String toString() {
        return String.format("baseCurrency=%s, targetCurrency=%s, Rate=%f, Amount=%f, ConvertedAmount=%f",
                baseCurrency.getCode(), targetCurrency.code, rate, amount, convertedAmount);
    }
}
