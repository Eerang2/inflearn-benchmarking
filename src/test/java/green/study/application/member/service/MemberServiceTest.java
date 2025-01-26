package green.study.application.member.service;

import green.study.application.common.BaseTest;
import green.study.domain.member.entity.MemberEntity;
import green.study.domain.member.enums.MemberType;
import green.study.domain.member.exceptions.registers.MemberIdValidateException;
import green.study.domain.member.exceptions.registers.PasswordValidateException;
import green.study.domain.member.model.Member;
import green.study.infrastructure.repository.MemberRepository;
import green.study.presentation.member.dto.MemberRes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest extends BaseTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @DisplayName("회원 가입 성공")
    public void success_signUp() {
        Member member = Member.builder()
                .memberId("name123")
                .password("qwer1234!")
                .name("리진우")
                .type(MemberType.USER_TYPE)
                .build();

        memberService.signup(member);

        MemberEntity savedMember = memberRepository.findByMemberId(member.getMemberId()).orElseThrow(() -> new MemberIdValidateException(member.getMemberId()));
        assertThat(savedMember.getMemberId()).isEqualTo(member.getMemberId());
    }

    @Test
    @Transactional
    @DisplayName("아이디 회원 가입 실패_길이")
    public void memberId_failedByLength() {

        // 3자리
        Member member1 = Member.builder()
                .memberId("abc")
                .password("qwer1234!")
                .name("리진우")
                .type(MemberType.USER_TYPE)
                .build();


        // 13자리
        Member member2 = Member.builder()
                .memberId("abcdefghijklm")
                .password("qwer1234!")
                .name("리진우")
                .type(MemberType.USER_TYPE)
                .build();

        // 예외가 발생하는지 확인
        MemberIdValidateException shortIdException1 = assertThrows(MemberIdValidateException.class, () -> {
            memberService.signup(member1);
        });
        MemberIdValidateException LongIdException2 = assertThrows(MemberIdValidateException.class, () -> {
            memberService.signup(member2);
        });

        // 예외 문구 확인
        assertTrue(shortIdException1.getMessage().contains("아이디는 4자 이상 12자 이하입니다."));
        assertTrue(LongIdException2.getMessage().contains("아이디는 4자 이상 12자 이하입니다."));
    }

    @Test
    @Transactional
    @DisplayName("아이디 회원 가입 실패_문자")
    public void memberId_failedByInvalidCharacter() {

        // 한글사용
        Member member1 = Member.builder()
                .memberId("이진우")
                .password("qwer1234!")
                .name("이진우")
                .type(MemberType.USER_TYPE)
                .build();

        // 특수문자 사용
        Member member2 = Member.builder()
                .memberId("jinwoo!@#")
                .password("qwer1234!")
                .name("홍길동")
                .type(MemberType.USER_TYPE)
                .build();

        // 예외가 발생하는지 확인
        MemberIdValidateException shortIdException1 = assertThrows(MemberIdValidateException.class, () -> {
            memberService.signup(member1);
        });
        MemberIdValidateException LongIdException2 = assertThrows(MemberIdValidateException.class, () -> {
            memberService.signup(member2);
        });

        // 예외 문구 확인
        assertTrue(shortIdException1.getMessage().contains("아이디는 숫자와 영문자만 사용할수있습니다."));
        assertTrue(LongIdException2.getMessage().contains("아이디는 숫자와 영문자만 사용할수있습니다."));
    }

    @Test
    @Transactional
    @DisplayName("아이디 중복 검사")
    public void same_memberId() {

        String sameMemberId = "sameId";
        memberService.signup(Member.builder()
                                    .memberId(sameMemberId)
                                    .password("qwer1234!")
                                    .name("리진우")
                                    .type(MemberType.USER_TYPE)
                                    .build());


        Member memberBySameMemberId = Member.builder()
                .memberId(sameMemberId)
                .password("1234qwer!")
                .name("김진우")
                .type(MemberType.USER_TYPE)
                .build();

        MemberIdValidateException sameMemberIdException = assertThrows(MemberIdValidateException.class, () -> {
            memberService.signup(memberBySameMemberId);
        });

        // 예외 문구 확인
        assertTrue(sameMemberIdException.getMessage().contains("이미 존재하는 회원입니다."));
    }



    @Test
    @Transactional
    @DisplayName("비밀번호 회원 가입 실패_길이")
    public void password_failedByLength() {

        // 7자리
        Member member1 = Member.builder()
                .memberId("jinwoo123")
                .password("qwer123")
                .name("리진우")
                .type(MemberType.USER_TYPE)
                .build();


        // 예외가 발생하는지 확인
        PasswordValidateException shortIdException1 = assertThrows(PasswordValidateException.class, () -> {
            memberService.signup(member1);
        });

        // 예외 문구 확인
        assertTrue(shortIdException1.getMessage().contains("비밀번호는 8자 이상입니다."));
    }

    @Test
    @Transactional
    @DisplayName("비밀번호 회원 가입 실패_문자")
    public void password_failedByInvalidCharacter() {

        // 한글사용
        Member member1 = Member.builder()
                .memberId("jinwoo123")
                .password("비밀번호비밀번호")
                .name("이진우")
                .type(MemberType.USER_TYPE)
                .build();

        // 영어만 사용
        Member member2 = Member.builder()
                .memberId("jinwoo123")
                .password("qwerqwer")
                .name("홍길동")
                .type(MemberType.USER_TYPE)
                .build();

        // 예외가 발생하는지 확인
            PasswordValidateException shortIdException1 = assertThrows(PasswordValidateException.class, () -> {
            memberService.signup(member1);
        });
        PasswordValidateException LongIdException2 = assertThrows(PasswordValidateException.class, () -> {
            memberService.signup(member2);
        });

        // 예외 문구 확인
        assertTrue(shortIdException1.getMessage().contains("비밀번호는 숫자와 영문자, 특수문자를 포함해야합니다."));
        assertTrue(LongIdException2.getMessage().contains("비밀번호는 숫자와 영문자, 특수문자를 포함해야합니다."));
    }

    @Test
    @Transactional
    @DisplayName("로그인 성공")
    void login_success() {
        Member createMember = Member.builder()
                .memberId("jinwoo123")
                .password("qwer1234!")
                .name("홍길동")
                .type(MemberType.USER_TYPE)
                .build();
        memberService.signup(createMember);

        Member loginMember = Member.builder()
                .memberId("jinwoo123")
                .password("qwer1234!")
                .build();
        MemberRes memberRes = memberService.loginAndGenerateToken(loginMember);
        assertNotNull(memberRes);
        assertNotNull(memberRes.getToken());
        assertThat(memberRes.getKey()).isEqualTo(1L);
    }

    @Test
    @Transactional
    @DisplayName("로그인 실패")
    void login_failed() {
        Member createMember = Member.builder()
                .memberId("jinwoo123")
                .password("qwer1234!")
                .name("홍길동")
                .type(MemberType.USER_TYPE)
                .build();
        memberService.signup(createMember);

        Member loginMember = Member.builder()
                .memberId("jinwoo12")
                .password("qwer1234!")
                .build();

        MemberIdValidateException wrongMemberId = assertThrows(MemberIdValidateException.class, () -> {
            memberService.loginAndGenerateToken(loginMember);
        });
        assertTrue(wrongMemberId.getMessage().contains("사용자를 찾을 수 없습니다."));
    }
}