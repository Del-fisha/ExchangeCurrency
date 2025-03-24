package pet.exchangecurrency.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.exchangecurrency.dto.ExchangeConvertedDto;
import pet.exchangecurrency.model.ExchangeRate;
import pet.exchangecurrency.repository.ExchangeRateRepository;
import pet.exchangecurrency.utilits.DtoConverter;

@Service
@RequiredArgsConstructor
public class ExchangeService {

    final ExchangeRateRepository exchangeRateRepository;

    public ExchangeConvertedDto getExchange(
            String baseCurrencyCode,
            String targetCurrencyCode,
            double amount) {

        ExchangeRate exchangeRate = exchangeRateRepository.findByBaseCurrencyCodeLikeAndTargetCurrencyCode(
                baseCurrencyCode.toUpperCase(),
                targetCurrencyCode.toUpperCase());

        ExchangeConvertedDto exchangeDto = DtoConverter.convertExchangeRateToConvertDto(exchangeRate);
        exchangeDto.setAmount(amount);
        exchangeDto.setConvertedAmount(exchangeDto.getAmount() * exchangeDto.getRate());

        return exchangeDto;
    }
}
