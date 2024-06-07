package Chaeda_spring.domain.review_note.folder_and_pdf.entity;

import Chaeda_spring.domain.member.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewNoteFolderRepository extends JpaRepository<ReviewNoteFolder, Long> {

    List<ReviewNoteFolder> findAllByStudent(Student student);

}
