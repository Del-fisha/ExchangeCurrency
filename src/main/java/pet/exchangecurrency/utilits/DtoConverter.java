package pet.exchangecurrency.utilits;

import org.springframework.beans.factory.annotation.Autowired;
import pet.exchangecurrency.dto.CurrencyDto;
import pet.exchangecurrency.dto.ExchangeConvertedDto;
import pet.exchangecurrency.dto.ExchangeRateDto;
import pet.exchangecurrency.model.Currency;
import pet.exchangecurrency.model.ExchangeRate;
import pet.exchangecurrency.repository.CurrencyRepository;
import pet.exchangecurrency.service.CurrencyCrudService;
import pet.exchangecurrency.service.ExchangeCrudService;

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

    public static ExchangeRateDto convertExchangeRateToDto(ExchangeRate rate) {
        return new ExchangeRateDto(rate.getId(),
                rate.getBaseCurrency().getCode(),
                rate.getTargetCurrency().getCode(),
                rate.getRate());
    }

    public static ExchangeConvertedDto convertExchangeRateToConvertDto(ExchangeRate rate) {
        ExchangeRateDto rateDto = new ExchangeRateDto(
                rate.getId(),
                rate.getBaseCurrency().getCode(),
                rate.getTargetCurrency().getCode(),
                rate.getRate());

        return new ExchangeConvertedDto(rateDto);
    }
}
