package green.study.presentation.interceptor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtInterceptorHelper {

    public String extractAccessTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            Cookie[] cookies = request.getCookies();

            for (Cookie c : cookies) {
                if ("JWT_TOKEN".equals(c.getName())) {
                    return c.getValue();
                }
            }
        }
        throw new IllegalArgumentException("비정상적인 토큰입니다.");
    }
}
