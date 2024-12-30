package green.study.domain.exceptions.registers;

public class PasswordValidateException extends RuntimeException {
    public PasswordValidateException(String message) {
        super (message);
    }
}
