package green.study.domain.model;

import green.study.domain.exceptions.ExpiredTokenException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Token {
    private String token;

    public static void validateToken(Token token) {
        if (token == null) {
            throw new ExpiredTokenException();
        }
    }
}
