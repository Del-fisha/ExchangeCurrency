package pet.exchangecurrency.exceptions.exchangeRate;

public class MissingCurrencyPairCodesException extends RuntimeException {
    public MissingCurrencyPairCodesException() {
        super("Коды валют пары отсутствуют в адресе");
    }
}
