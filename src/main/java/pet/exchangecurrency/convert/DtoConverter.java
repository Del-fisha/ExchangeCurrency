package pet.exchangecurrency.convert;

import pet.exchangecurrency.dto.CurrencyDto;
import pet.exchangecurrency.model.Currency;

public class DtoConverter {

    public static CurrencyDto convertCurrencyToDto(Currency currency) {

        return new CurrencyDto(
                currency.getId(),
                currency.getCode(),
                currency.getFullName(),
                currency.getSign());
    }

    public static Currency convertDtoToCurrency(CurrencyDto dto) {
        Currency currency =  new Currency();
        currency.setCode(dto.getCode());
        currency.setSign(dto.getSign());
        currency.setFullName(dto.getFullName());

        return currency;
    }
}
