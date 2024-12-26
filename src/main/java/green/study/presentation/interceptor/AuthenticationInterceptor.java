package green.study.presentation.interceptor;

import green.study.domain.member.model.Member;
import green.study.domain.exceptions.ExpiredTokenException;
import green.study.domain.exceptions.InvalidTokenException;
import green.study.infrastructure.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;
    private final JwtInterceptorHelper jwtInterceptorHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            String accessToken = jwtInterceptorHelper.extractAccessTokenFromRequest(request);
            System.out.println("accessToken: " + accessToken);
            Member user = jwtUtil.getLoginUserFromAccessToken(accessToken);
            System.out.println("member name authenticate : " + user.getMemberId());
            System.out.println("member type authenticate : " + user.getId());
            System.out.println("member id authenticate : " + user.getType());
            request.setAttribute("user", user);
        } catch (ExpiredTokenException ete) {
            // 만료된 토큰일 경우 리프레시 토큰 로직을 추가할 수 있음
            request.setAttribute("user", null);  // 로그인되지 않음
        } catch (InvalidTokenException ite) {
            request.setAttribute("user", null);  // 로그인되지 않음
        }
        System.out.println("true");
        return true;
    }
}
