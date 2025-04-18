package pet.exchangecurrency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pet.exchangecurrency.dto.ExchangeRateDto;
import pet.exchangecurrency.service.ExchangeCrudService;

@RestController
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeCrudService exchangeCrudService;

    @PostMapping("/exchangerates")
    public ResponseEntity<?> create(
            @RequestParam("baseCurrencyCode") String baseCurrencyCode,
            @RequestParam("targetCurrencyCode") String targetCurrencyCode,
            @RequestParam("rate") double rate) {
        try {
            ExchangeRateDto result = exchangeCrudService.create(baseCurrencyCode, targetCurrencyCode, rate);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/exchangerates/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            exchangeCrudService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException argumentException) {
            return new ResponseEntity<>(argumentException, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/exchangerates")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(exchangeCrudService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/exchangerates/{codecode}")
    public ResponseEntity<?> getByCode(@PathVariable String codecode) {
        try {
            ExchangeRateDto result = exchangeCrudService.getByCode(codecode);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/exchangerates")
    public ResponseEntity<?> update(@ModelAttribute ExchangeRateDto dto) {
        try {
            ExchangeRateDto result = exchangeCrudService.update(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException argumentException) {
            return new ResponseEntity<>(argumentException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/exchangerates/{codecode}")
    public ResponseEntity<?> patch(@PathVariable String codecode, @RequestParam double rate) {
        try {
            return new ResponseEntity<>(exchangeCrudService.updateRate(codecode, rate), HttpStatus.OK);
        } catch (IllegalArgumentException argumentException) {
            return new ResponseEntity<>(argumentException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

