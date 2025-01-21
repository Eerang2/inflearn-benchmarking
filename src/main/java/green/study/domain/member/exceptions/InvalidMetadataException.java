package green.study.domain.member.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidMetadataException extends RuntimeException {
    public InvalidMetadataException(String message, Throwable cause) {
        super(message, cause);
    }
}
