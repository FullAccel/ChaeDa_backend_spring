package Chaeda_spring.domain.File.entity;

import Chaeda_spring.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findAllByMember(Member member);
}
