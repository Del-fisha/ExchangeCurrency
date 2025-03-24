package pet.exchangecurrency.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.exchangecurrency.dto.ExchangeRateDto;
import pet.exchangecurrency.exceptions.exchangeRate.CurrencyPairNotExistingException;
import pet.exchangecurrency.exceptions.exchangeRate.CurrencyPairNotFoundException;
import pet.exchangecurrency.exceptions.exchangeRate.DuplicateCurrencyPairException;
import pet.exchangecurrency.exceptions.exchangeRate.ExchangeRateNotFoundException;
import pet.exchangecurrency.model.Currency;
import pet.exchangecurrency.model.ExchangeRate;
import pet.exchangecurrency.repository.CurrencyRepository;
import pet.exchangecurrency.repository.ExchangeRateRepository;
import pet.exchangecurrency.utilits.CodeSplitUtility;
import pet.exchangecurrency.utilits.DtoConverter;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeCrudService implements CrudServiceInterface<ExchangeRateDto> {

    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;


    @Override
    public ExchangeRateDto create(ExchangeRateDto dto) {
        ExchangeRate rate =
                exchangeRateRepository.findByBaseCurrencyCodeLikeAndTargetCurrencyCode(
                        dto.getBaseCurrency().getCode().toUpperCase(),
                        dto.getTargetCurrency().getCode().toUpperCase());
        if (rate != null) {
            throw new DuplicateCurrencyPairException();
        } else {
            rate = new ExchangeRate();
            rate.setBaseCurrency(currencyRepository.findByCode(dto.getBaseCurrency().getCode().toUpperCase()));
            rate.setTargetCurrency(currencyRepository.findByCode(dto.getTargetCurrency().getCode().toUpperCase()));
            if (rate.getBaseCurrency() == null || rate.getTargetCurrency() == null) {
                throw new CurrencyPairNotExistingException();
            }
            rate.setRate(dto.getRate());
        }

        ExchangeRate savedRate = exchangeRateRepository.save(rate);
        return DtoConverter.convertExchangeRateToDto(savedRate);
    }

    public ExchangeRateDto create(String baseCurrencyCode, String targetCurrencyCode, double rate) {
        ExchangeRate savedExchangeRate =
                exchangeRateRepository.findByBaseCurrencyCodeLikeAndTargetCurrencyCode(
                        baseCurrencyCode,
                        targetCurrencyCode);
        if (savedExchangeRate != null) {
            throw new DuplicateCurrencyPairException();
        }
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setBaseCurrency(currencyRepository.findByCode(baseCurrencyCode.toUpperCase()));
        exchangeRate.setTargetCurrency(currencyRepository.findByCode(targetCurrencyCode.toUpperCase()));
        if (exchangeRate.getBaseCurrency() == null || exchangeRate.getTargetCurrency() == null) {
            throw new CurrencyPairNotExistingException();
        }

        exchangeRate.setRate(rate);

        ExchangeRate savedRate = exchangeRateRepository.save(exchangeRate);
        return DtoConverter.convertExchangeRateToDto(savedRate);
    }

    @Override
    public ExchangeRateDto getById(long id) {
        ExchangeRate rate = exchangeRateRepository.findById(id).orElse(null);
        if (rate == null) {
            throw new CurrencyPairNotFoundException();
        }
        return DtoConverter.convertExchangeRateToDto(rate);
    }

    public ExchangeRateDto getByCode(String code) {
        Currency baseCurrency = currencyRepository
                .findByCode(CodeSplitUtility.splitCode(code).get(0).toUpperCase());
        Currency targetCurrency = currencyRepository
                .findByCode(CodeSplitUtility.splitCode(code).get(1).toUpperCase());

        if (baseCurrency == null || targetCurrency == null) {
            throw new CurrencyPairNotExistingException();
        }
        ExchangeRate rate = exchangeRateRepository
                .findByBaseCurrencyCodeLikeAndTargetCurrencyCode(
                        baseCurrency.getCode(),
                        targetCurrency.getCode());

        return DtoConverter.convertExchangeRateToDto(rate);
    }

    @Override
    public Collection<ExchangeRateDto> getAll() {
        Collection<ExchangeRate> rates = exchangeRateRepository.findAll();
        return rates.stream().map(DtoConverter::convertExchangeRateToDto).collect(Collectors.toList());
    }

    @Override
    public ExchangeRateDto update(ExchangeRateDto dto) {
        ExchangeRate exchangeRateToUpdate =
                exchangeRateRepository.findById(dto.getId()).orElse(null);

        if (exchangeRateToUpdate == null) {
            throw new ExchangeRateNotFoundException();
        }

        exchangeRateToUpdate.setBaseCurrency(currencyRepository.findByCode(dto.getBaseCurrency().getCode().toUpperCase()));
        exchangeRateToUpdate.setTargetCurrency(currencyRepository.findByCode(dto.getTargetCurrency().getCode().toUpperCase()));
        exchangeRateToUpdate.setRate(dto.getRate());

        ExchangeRate savedRate = exchangeRateRepository.save(exchangeRateToUpdate);

        return DtoConverter.convertExchangeRateToDto(savedRate);
    }

    public ExchangeRateDto updateRate(String code, Double rate) {
        Currency baseCurrency = currencyRepository
                .findByCode(CodeSplitUtility.splitCode(code).get(0).toUpperCase());
        Currency targetCurrency = currencyRepository
                .findByCode(CodeSplitUtility.splitCode(code).get(1).toUpperCase());

        if (baseCurrency == null || targetCurrency == null) {
            throw new CurrencyPairNotExistingException();
        }
        ExchangeRate rateToUpdate = exchangeRateRepository
                .findByBaseCurrencyCodeLikeAndTargetCurrencyCode(
                        baseCurrency.getCode(),
                        targetCurrency.getCode());
        rateToUpdate.setRate(rate);
        ExchangeRate savedRate = exchangeRateRepository.save(rateToUpdate);

        return DtoConverter.convertExchangeRateToDto(savedRate);
    }

    @Override
    public void deleteById(long id) {
        try {
            exchangeRateRepository.deleteById(id);
        } catch (IllegalArgumentException argumentException) {
            throw new ExchangeRateNotFoundException();
        }
    }
}
