package green.study.presentation.interceptor;

import green.study.domain.member.LoginUser;
import green.study.domain.member.model.Member;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest servletRequest = (HttpServletRequest) webRequest.getNativeRequest();

        // request에서 "loginUser" 속성 가져오기
        Object objectLoginUser = servletRequest.getAttribute("user");
        System.out.println("objectLoginUser = " + objectLoginUser);

        if (objectLoginUser != null && objectLoginUser instanceof Member) {
            System.out.println("mksdmlksmf");
            return (Member)objectLoginUser;
        }
        return null;  // 로그인되지 않았으면 null 반환
    }
}
