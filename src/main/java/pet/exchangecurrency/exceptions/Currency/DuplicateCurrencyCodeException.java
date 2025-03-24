package pet.exchangecurrency.exceptions.Currency;

public class DuplicateCurrencyCodeException extends RuntimeException {
    public DuplicateCurrencyCodeException() {
        super("Валюта с таким кодом существует");
    }
}
