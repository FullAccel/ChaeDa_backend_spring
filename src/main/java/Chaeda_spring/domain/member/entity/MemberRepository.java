package Chaeda_spring.domain.member.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsMemberByEmail(String email);

    Optional<Member> findMemberByLoginId(String loginId);
}
