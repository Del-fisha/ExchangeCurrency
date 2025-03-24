package pet.exchangecurrency.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.exchangecurrency.dto.CurrencyDto;
import pet.exchangecurrency.exceptions.Currency.CurrencyNotFoundException;
import pet.exchangecurrency.exceptions.Currency.DuplicateCurrencyCodeException;
import pet.exchangecurrency.exceptions.Currency.MissingCodeException;
import pet.exchangecurrency.exceptions.general.MissingFormFieldException;
import pet.exchangecurrency.model.Currency;
import pet.exchangecurrency.repository.CurrencyRepository;
import pet.exchangecurrency.utilits.DtoConverter;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyCrudService implements CrudServiceInterface<CurrencyDto> {

    private final CurrencyRepository currencyRepository;

    @Override
    public CurrencyDto create(CurrencyDto dto) {
        if (dto == null || dto.getCode().isEmpty() || dto.getSign().isEmpty() || dto.getFullName().isEmpty()) {
            throw new MissingFormFieldException();
        }

        Currency currencyDto = currencyRepository.findByCode(dto.getCode());
        if (currencyDto != null) {
            throw new DuplicateCurrencyCodeException();
        }

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
            throw new CurrencyNotFoundException();
        }
        return DtoConverter.convertCurrencyToDto(currency);
    }

    public CurrencyDto getByCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new MissingCodeException();
        }
        Currency currency = currencyRepository.findByCode(code.toUpperCase());
        if (currency == null) {
            throw new CurrencyNotFoundException();
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
            throw new CurrencyNotFoundException();
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
            throw new CurrencyNotFoundException();
        }
        currencyRepository.delete(currency);
    }

    public void deleteByCode(String code) {
        Currency currency = currencyRepository.findByCode(code.toUpperCase());
        if (currency == null) {
            throw new CurrencyNotFoundException();
        }
        currencyRepository.delete(currency);
    }
}
