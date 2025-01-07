package green.study.application.member;

import green.study.domain.member.enums.MemberType;
import green.study.domain.member.exceptions.ExpiredTokenException;
import green.study.domain.member.exceptions.InvalidTokenException;
import green.study.domain.member.model.Member;
import green.study.infrastructure.util.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    @DisplayName("토큰생성해보기")
    void 토큰생성해보기() {
        String newToken = jwtUtil.createAccessToken(new Member(7L, "jinwoo12", MemberType.USER_TYPE));
        Assertions.assertNotEquals("", newToken);
    }

    @Test
    @DisplayName("토큰에서_회원번호_꺼내보기")
    void 토큰에서_회원번호_꺼내보기() {
        String accessToken = jwtUtil.createAccessToken(new Member(7L, "jinwoo12", MemberType.USER_TYPE));
        Member member = jwtUtil.getLoginUserFromAccessToken(accessToken);
        Assertions.assertEquals(7L,member.getKey());
    }

    @Test
    @DisplayName("비정상토큰일 경우 InvalidTokenException이 나는지")
    void 비정상토큰이면특정예외발생() {
        String invalidToken = "not token";

        InvalidTokenException invalidTokenException = Assertions.assertThrows(InvalidTokenException.class, () -> {
            jwtUtil.getLoginUserFromAccessToken(invalidToken);
        });

        assertThat(invalidTokenException.getMessage()).contains("비정상적인 토큰이래요~");
    }

    @Test
    @DisplayName("만료된토큰의 경우 ExpiredTokenException이 나는지")
    void 만료된토큰이면특정예외발생() {
        Assertions.assertThrows(ExpiredTokenException.class, () -> {
            String accessToken = jwtUtil.createAccessToken(new Member(7L, "jinwoo12", MemberType.USER_TYPE), 200L);
            Thread.sleep(1000); // 200밀리세컨후가 만료인데 1000밀리세컨 후 확인하게되니 당근 만료되었겠지?
            jwtUtil.getLoginUserFromAccessToken(accessToken);
        });
    }
}
