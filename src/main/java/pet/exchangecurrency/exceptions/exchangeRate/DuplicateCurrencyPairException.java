package pet.exchangecurrency.exceptions.exchangeRate;

public class DuplicateCurrencyPairException extends RuntimeException {
    public DuplicateCurrencyPairException() {
        super("Валютная пара с таким кодом существует");
    }
}
