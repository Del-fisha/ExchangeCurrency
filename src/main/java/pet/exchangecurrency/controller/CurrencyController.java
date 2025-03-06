package pet.exchangecurrency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.exchangecurrency.dto.CurrencyDto;
import pet.exchangecurrency.service.CurrencyCrudService;

import java.util.Collection;
import java.util.List;

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
}
