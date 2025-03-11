package pet.exchangecurrency.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.exchangecurrency.dto.ExchangeRateDto;
import pet.exchangecurrency.model.Currency;
import pet.exchangecurrency.model.ExchangeRate;
import pet.exchangecurrency.repository.CurrencyRepository;
import pet.exchangecurrency.repository.ExchangeRateRepository;
import pet.exchangecurrency.utilits.CodeSplitUtility;
import pet.exchangecurrency.utilits.DtoConverter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExchangeCrudService implements CrudServiceInterface<ExchangeRateDto> {

    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;


    @Override
    public ExchangeRateDto create(ExchangeRateDto dto) {
        ExchangeRate rate = new ExchangeRate();
        rate.setBaseCurrency(currencyRepository.findByCode(dto.getBaseCurrencyCode()));
        rate.setTargetCurrency(currencyRepository.findByCode(dto.getTargetCurrencyCode()));
        rate.setRate(dto.getRate());

        ExchangeRate savedRate = exchangeRateRepository.save(rate);
        return DtoConverter.convertExchangeRateToDto(savedRate);
    }

    @Override
    public ExchangeRateDto getById(long id) {
        ExchangeRate rate = exchangeRateRepository.findById(id).orElse(null);
        if (rate == null) {
            throw new IllegalArgumentException();
        }
        return DtoConverter.convertExchangeRateToDto(rate);
    }

    public ExchangeRateDto getByCode(String code) {
        Currency baseCurrency = currencyRepository.findByCode(CodeSplitUtility.splitCode(code).get(0).toUpperCase());
        Currency targetCurrency = currencyRepository.findByCode(CodeSplitUtility.splitCode(code).get(1).toUpperCase());

        List<ExchangeRate> allByBaseCurrencyLike =
                exchangeRateRepository.findAllByBaseCurrencyCodeLike(baseCurrency.getCode());
        ExchangeRate rate = allByBaseCurrencyLike.stream()
                .filter(r -> r.getTargetCurrency().equals(targetCurrency))
                .findFirst().orElse(null);

        System.out.println(rate);
        if (allByBaseCurrencyLike.isEmpty() || rate == null) {
            throw new IllegalArgumentException();
        }

        return DtoConverter.convertExchangeRateToDto(rate);
    }

    @Override
    public Collection<ExchangeRateDto> getAll() {
        Collection<ExchangeRate> rates = exchangeRateRepository.findAll();
        return rates.stream().map(DtoConverter::convertExchangeRateToDto).collect(Collectors.toList());
    }

    @Override
    public ExchangeRateDto update(ExchangeRateDto dto) {
        return null;
    }

    @Override
    public void deleteById(long id) {
        try {
            exchangeRateRepository.deleteById(id);
        } catch (IllegalArgumentException argumentException) {
            throw new RuntimeException();
        }
    }
}
