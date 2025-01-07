package green.study.infrastructure.repository;

import green.study.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByMemberId(String userId);

    Optional<MemberEntity> findByMemberId(String memberId);

    Optional<MemberEntity> findByKey(Long key);
}
