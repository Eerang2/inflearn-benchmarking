package green.study.domain.member.exceptions.registers;

public class PasswordValidateException extends RuntimeException {
    public PasswordValidateException(String message) {
        super (message);
    }
}
