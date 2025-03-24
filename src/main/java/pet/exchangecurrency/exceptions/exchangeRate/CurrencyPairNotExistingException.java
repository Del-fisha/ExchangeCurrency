package pet.exchangecurrency.exceptions.exchangeRate;

public class CurrencyPairNotExistingException extends RuntimeException {
    public CurrencyPairNotExistingException() {
        super("Одна или обе валют не существуют");
    }
}
