package green.study.domain.exceptions.registers;

public class MemberIdValidateException extends RuntimeException {
    public MemberIdValidateException(String message) {
        super(message);
    }
}
