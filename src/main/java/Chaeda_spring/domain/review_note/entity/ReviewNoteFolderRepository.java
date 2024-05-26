package Chaeda_spring.domain.review_note.entity;

import Chaeda_spring.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewNoteFolderRepository extends JpaRepository<ReviewNoteFolder, Long> {

    List<ReviewNoteFolder> findAllByMember(Member member);

}
