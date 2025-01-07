package green.study.infrastructure.util;

import green.study.domain.member.model.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private static final String secretKey = "b2d6f5c79a2b2f1db939f04e3dbd6d9bdb56c07b557073d2a0c24fd4faec0a4f";

    private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    Long EXPIRATION_TIME_MS = 1000 * 60 * 60 * 24L; // 밀리세컨이라 1000 * 60초 * 60분 * 24시 => 하루
    private static final String USER_NO_KEY_NAME = "id";
    private final String USER_ID_KEY_NAME = "name";

    public String createAccessToken(final Member loginUser) {
        return this.createAccessToken(loginUser, EXPIRATION_TIME_MS);
    }

    public String createAccessToken(final Member loginUser, final long expirationTimeMs) {
        String token = Jwts.builder()
                .claim(USER_NO_KEY_NAME, loginUser.getKey())
                .claim(USER_ID_KEY_NAME, loginUser.getMemberId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTimeMs))
                .signWith(key)
                .compact();
        log.debug("created token : {} ", token);
        return token;
    }

    public Member getLoginUserFromAccessToken(final String accessToken) {
        Claims claims = getClaims(accessToken);

        return Member.builder()
                .key(claims.get(USER_NO_KEY_NAME, Long.class))
                .memberId(claims.get(USER_ID_KEY_NAME, String.class))
                .build();
    }

    private Claims getClaims(final String accessToken) {
        Claims claims ;
        try {
            claims = Jwts.parser()
                    .verifyWith(key) // 단순히 key 타입만 검증하더라...
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload();
        } catch(ExpiredJwtException eje) { // 만료된 토큰일 경우 발생하는 Exception
            throw new IllegalArgumentException("No Token"); // 내가 만든 Exception으로 바꿔서 던짐 -> 리프레시토큰 로직으로 분기되어야함
        } catch(Exception e) { // 기타 나머지(변조되었거나, 형식이 안맞거나 등등등)는 퉁쳐서 비정상 토큰으로 간주
            throw new IllegalArgumentException("Invalid Token");
        }
        return claims;
    }

}
