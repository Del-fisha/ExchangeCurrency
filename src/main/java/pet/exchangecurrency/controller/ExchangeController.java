package pet.exchangecurrency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.exchangecurrency.dto.ExchangeConvertedDto;
import pet.exchangecurrency.service.ExchangeService;

@RestController
@RequiredArgsConstructor
public class ExchangeController {

    final ExchangeService exchangeService;

    @GetMapping("/exchange")
    public ResponseEntity<?> getExchange(
            @RequestParam("from") String baseCurrencyCode,
            @RequestParam("to") String targetCurrencyCode,
            @RequestParam("amount") double amount) {
        try {
            return ResponseEntity.ok(exchangeService.getExchange(baseCurrencyCode, targetCurrencyCode, amount));
        } catch (IllegalArgumentException argumentException) {
            return new ResponseEntity<>(argumentException.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
