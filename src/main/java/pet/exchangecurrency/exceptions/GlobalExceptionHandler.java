package pet.exchangecurrency.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pet.exchangecurrency.exceptions.Currency.CurrencyNotFoundException;
import pet.exchangecurrency.exceptions.Currency.DuplicateCurrencyCodeException;
import pet.exchangecurrency.exceptions.Currency.MissingCodeException;
import pet.exchangecurrency.exceptions.exchangeRate.*;
import pet.exchangecurrency.exceptions.general.MissingFormFieldException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<BadResponse> handleCurrencyNotFoundException(CurrencyNotFoundException ex) {
        BadResponse badResponse = new BadResponse(ex.getMessage());
        return new ResponseEntity<>(badResponse, HttpStatus.valueOf(404));
    }

    @ExceptionHandler(MissingCodeException.class)
    public ResponseEntity<BadResponse> handleMissingCodeException(MissingCodeException ex) {
        BadResponse badResponse = new BadResponse(ex.getMessage());
        return new ResponseEntity<>(badResponse, HttpStatus.valueOf(400));
    }

    @ExceptionHandler(DuplicateCurrencyCodeException.class)
    public ResponseEntity<BadResponse> handleDuplicateCurrencyCodeException(DuplicateCurrencyCodeException ex) {
        BadResponse badResponse = new BadResponse(ex.getMessage());
        return new ResponseEntity<>(badResponse, HttpStatus.valueOf(409));
    }

    @ExceptionHandler(MissingFormFieldException.class)
    public ResponseEntity<BadResponse> handleMissingFormFieldException(MissingFormFieldException ex) {
        BadResponse badResponse = new BadResponse(ex.getMessage());
        return new ResponseEntity<>(badResponse, HttpStatus.valueOf(400));
    }

    @ExceptionHandler(MissingCurrencyPairCodesException.class)
    public ResponseEntity<BadResponse> handleMissingCurrencyPairCodesException(MissingCurrencyPairCodesException ex) {
        BadResponse badResponse = new BadResponse(ex.getMessage());
        return new ResponseEntity<>(badResponse, HttpStatus.valueOf(400));
    }

    @ExceptionHandler(ExchangeRateNotFoundException.class)
    public ResponseEntity<BadResponse> handleExchangeRateNotFoundException(ExchangeRateNotFoundException ex) {
        BadResponse badResponse = new BadResponse(ex.getMessage());
        return new ResponseEntity<>(badResponse, HttpStatus.valueOf(404));
    }

    @ExceptionHandler(DuplicateCurrencyPairException.class)
    public ResponseEntity<BadResponse> handleDuplicateCurrencyPairException(DuplicateCurrencyPairException ex) {
        BadResponse badResponse = new BadResponse(ex.getMessage());
        return new ResponseEntity<>(badResponse, HttpStatus.valueOf(409));
    }

    @ExceptionHandler(CurrencyPairNotExistingException.class)
    public ResponseEntity<BadResponse> handleCurrencyNotExistingException(CurrencyPairNotExistingException ex) {
        BadResponse badResponse = new BadResponse(ex.getMessage());
        return new ResponseEntity<>(badResponse, HttpStatus.valueOf(404));
    }

    @ExceptionHandler(CurrencyPairNotFoundException.class)
    public ResponseEntity<BadResponse> handleCurrencyPairNotFoundException(CurrencyPairNotFoundException ex) {
        BadResponse badResponse = new BadResponse(ex.getMessage());
        return new ResponseEntity<>(badResponse, HttpStatus.valueOf(404));
    }


}
