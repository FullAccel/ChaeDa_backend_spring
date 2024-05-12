package Chaeda_spring.domain.statistics.entity.problem_type_statistics;

import Chaeda_spring.domain.member.entity.Student;
import Chaeda_spring.global.constant.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccumulatedStatisticsForChapterRepository extends JpaRepository<AccumulatedStatisticsForChapter, Long> {

    AccumulatedStatisticsForChapter findByStudentAndChapter(Student student, Chapter chapter);

}
