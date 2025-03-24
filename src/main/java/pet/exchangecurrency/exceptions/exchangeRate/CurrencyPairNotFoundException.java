package pet.exchangecurrency.exceptions.exchangeRate;

public class CurrencyPairNotFoundException extends RuntimeException {
    public CurrencyPairNotFoundException() {
        super("Валютная пара отсутствует в базе данных");
    }
}
