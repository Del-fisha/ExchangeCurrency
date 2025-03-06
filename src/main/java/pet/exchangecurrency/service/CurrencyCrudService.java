package pet.exchangecurrency.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.exchangecurrency.convert.DtoConverter;
import pet.exchangecurrency.dto.CurrencyDto;
import pet.exchangecurrency.model.Currency;
import pet.exchangecurrency.repository.CurrencyRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyCrudService implements CrudServiceInterface<CurrencyDto> {

    private final CurrencyRepository currencyRepository;

    @Override
    public CurrencyDto create(CurrencyDto dto) {
        Currency currency = new Currency();
        currency.setCode(dto.getCode());
        currency.setFullName(dto.getFullName());
        currency.setSign(dto.getSign());

        Currency savedCurrency = currencyRepository.save(currency);
        return DtoConverter.convertCurrencyToDto(savedCurrency);
    }

    @Override
    public CurrencyDto getById(long id) {
        Currency currency = currencyRepository.findById(id).orElse(null);

        return null;
    }

    public CurrencyDto getCurrent(String code) {
        return null;
    }

    @Override
    public Collection<CurrencyDto> getAll() {
        return currencyRepository.findAll()
                .stream()
                .map(DtoConverter::convertCurrencyToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void update(CurrencyDto dto) {

    }

    @Override
    public void delete(long id) {

    }

}
