package site.devdalus.ariadne.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.devdalus.ariadne.domain.Answer;
import site.devdalus.ariadne.domain.Node;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
}
