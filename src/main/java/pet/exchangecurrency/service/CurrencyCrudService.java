package pet.exchangecurrency.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.exchangecurrency.utilits.DtoConverter;
import pet.exchangecurrency.dto.CurrencyDto;
import pet.exchangecurrency.model.Currency;
import pet.exchangecurrency.repository.CurrencyRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyCrudService implements CrudServiceInterface<CurrencyDto> {

    private final CurrencyRepository currencyRepository;

    @Override
    public CurrencyDto create(CurrencyDto dto) {
        Currency currency = new Currency();
        currency.setCode(dto.getCode().toUpperCase());
        currency.setFullName(dto.getFullName());
        currency.setSign(dto.getSign());

        Currency savedCurrency = currencyRepository.save(currency);
        return DtoConverter.convertCurrencyToDto(savedCurrency);
    }

    @Override
    public CurrencyDto getById(long id) {
        Currency currency = currencyRepository.findById(id).orElse(null); // ToDo Обработать исключения
        if (currency == null) {
            throw new NullPointerException("Currency not found");
        }
        return DtoConverter.convertCurrencyToDto(currency);
    }

    public CurrencyDto getByCode(String code) {
        Currency currency = currencyRepository.findByCode(code.toUpperCase());
        if (currency == null) {
            throw new NullPointerException("Currency not found");
        }
        return DtoConverter.convertCurrencyToDto(currency);
    }

    @Override
    public Collection<CurrencyDto> getAll() {
        return currencyRepository.findAll()
                .stream()
                .map(DtoConverter::convertCurrencyToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CurrencyDto update(CurrencyDto dto) {
        Currency currency = currencyRepository.findById(dto.getId()).orElse(null);
        if (currency == null) {
            throw new NullPointerException("Currency not found");
        }
        currency.setCode(dto.getCode().toUpperCase());
        currency.setSign(dto.getSign());
        currency.setFullName(dto.getFullName());
        currency = currencyRepository.save(currency);

        return DtoConverter.convertCurrencyToDto(currency);
    }

    @Override
    public void deleteById(long id) {
        Currency currency = currencyRepository.findById(id).orElse(null);
        if (currency == null) {
            throw new NullPointerException("Currency not found");
        }
        currencyRepository.delete(currency);
    }

    public void deleteByCode(String code) {
        Currency currency = currencyRepository.findByCode(code.toUpperCase());
        if (currency == null) {
            throw new NullPointerException("Currency not found");
        }
        currencyRepository.delete(currency);
    }
}
