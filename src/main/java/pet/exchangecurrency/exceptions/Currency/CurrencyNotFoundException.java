package pet.exchangecurrency.exceptions.Currency;

public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException() {
        super("Валюта не найдена");
    }
}
