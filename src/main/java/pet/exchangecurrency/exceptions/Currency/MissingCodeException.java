package pet.exchangecurrency.exceptions.Currency;

public class MissingCodeException extends RuntimeException {

    public MissingCodeException() {
        super("Код валюты отсутствует в запросе");
    }
}
