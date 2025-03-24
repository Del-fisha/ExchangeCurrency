package pet.exchangecurrency.exceptions.general;

public class MissingFormFieldException extends RuntimeException {
    public MissingFormFieldException() {
        super("Отсутствует нужное поле формы");
    }
}
