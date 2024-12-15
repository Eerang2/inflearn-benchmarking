package green.study.infrastructure.repository;

import green.study.domain.admin.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<AdminEntity, Long> {
}
