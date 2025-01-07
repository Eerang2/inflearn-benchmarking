package green.study.application.member.repository;

import green.study.application.member.MemberService;
import green.study.domain.member.entity.MemberEntity;
import green.study.domain.member.enums.MemberType;
import green.study.domain.member.model.Member;
import green.study.infrastructure.repository.MemberRepository;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MemberRepositoryTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @DisplayName("유저 DB 정상 저장")
    void saveMember() {
        MemberEntity member = MemberEntity.builder()
                .memberId("jinwoo123")
                .password("qwer1234!")
                .name("이진우")
                .type(MemberType.USER_TYPE)
                .build();

        MemberEntity save = memberRepository.save(member);
        assertThat(save.getMemberId()).isNotNull();
        assertThat(save.getMemberId()).isEqualTo(member.getMemberId());
    }

    @Test
    @Transactional
    @DisplayName("유저 DB 바정상 저장")
    void saveMember_exception() {
        // 3자리
        MemberEntity memberIdShortLengthException = MemberEntity.builder()
                .memberId("abc")
                .password("qwer1234!")
                .name("이진우")
                .type(MemberType.USER_TYPE)
                .build();

        MemberEntity memberIdLongLengthException = MemberEntity.builder()
                .memberId("abcdefghijklm")
                .password("qwer1234!")
                .name("이진우")
                .type(MemberType.USER_TYPE)
                .build();

        ConstraintViolationException shortException = assertThrows(ConstraintViolationException.class, () -> {
            memberRepository.save(memberIdShortLengthException);
        });

        ConstraintViolationException LongException = assertThrows(ConstraintViolationException.class, () -> {
            memberRepository.save(memberIdLongLengthException);
        });

        // 예외 메시지 확인
        assertThat(shortException.getMessage()).contains("아이디는 4자 이상 12자 이하여야 합니다.");
        assertThat(LongException.getMessage()).contains("아이디는 4자 이상 12자 이하여야 합니다.");
    }

    @Test
    @Transactional
    @DisplayName("회원 꺼내오기")
    void findMember() {

        // Given
        Member member = Member.builder()
                .memberId("jinwoo123")
                .password("qwer1234!")
                .name("이진우")
                .type(MemberType.USER_TYPE)
                .build();
        memberService.signup(member);

        // When
        MemberEntity memberByMemberId = memberRepository.findByMemberId("jinwoo123").orElseThrow(() -> new IllegalArgumentException("null"));
        boolean existMember = memberRepository.existsByMemberId(member.getMemberId());

        // Then
        assertThat(memberByMemberId).isNotNull();
        assertThat(memberByMemberId.getMemberId()).isEqualTo(member.getMemberId());
        assertThat(existMember).isTrue();
    }
}
