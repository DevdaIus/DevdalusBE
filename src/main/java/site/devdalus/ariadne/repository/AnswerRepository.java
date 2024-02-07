package site.devdalus.ariadne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.devdalus.ariadne.domain.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}