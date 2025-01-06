package green.study.domain.member.exceptions.registers;

public class MemberIdValidateException extends RuntimeException {
    public MemberIdValidateException(String message) {
        super(message);
    }
}
