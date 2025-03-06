package pet.exchangecurrency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.exchangecurrency.dto.CurrencyDto;
import pet.exchangecurrency.service.CurrencyCrudService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyCrudService currencyService;

    @PostMapping("/currencies")
    public ResponseEntity<?> create(
            @RequestParam("code") String code,
            @RequestParam("fullName") String fullName,
            @RequestParam("sign") String sign) {
        try {
            CurrencyDto currencyDto = new CurrencyDto();
            currencyDto.setCode(code);
            currencyDto.setFullName(fullName);
            currencyDto.setSign(sign);

            CurrencyDto savedDto = currencyService.create(currencyDto);
            return new ResponseEntity<>(savedDto, HttpStatus.CREATED);

        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/currencies")
    public ResponseEntity<Collection<CurrencyDto>> getAll() {
        return new ResponseEntity<>(currencyService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/currency/id/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        try {
            CurrencyDto currencyDto = currencyService.getById(id);
            return new ResponseEntity<>(currencyDto, HttpStatus.OK);
        } catch (NullPointerException nullPointerException) {
            return new ResponseEntity<>(nullPointerException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/currency/{code}")
    public ResponseEntity<?> getByCode(@PathVariable("code") String code) {
        try{
            CurrencyDto currencyDto = currencyService.getByCode(code);
            return new ResponseEntity<>(currencyDto, HttpStatus.OK);
        } catch (NullPointerException nullPointerException) {
            return new ResponseEntity<>(nullPointerException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/currency/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        try {
            currencyService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NullPointerException nullPointerException) {
            return new ResponseEntity<>(nullPointerException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/currency/{code}")
    public ResponseEntity<?> deleteByCode(@PathVariable("code") String code) {
        try {
            currencyService.deleteByCode(code);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NullPointerException nullPointerException) {
            return new ResponseEntity<>(nullPointerException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/currencies")
    public ResponseEntity<?> update(CurrencyDto currencyDto) {
        try {
            CurrencyDto dto = currencyService.update(currencyDto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (NullPointerException nullPointerException) {
            return new ResponseEntity<>(nullPointerException.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
