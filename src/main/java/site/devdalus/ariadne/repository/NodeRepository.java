package site.devdalus.ariadne.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.devdalus.ariadne.domain.Answer;
import site.devdalus.ariadne.domain.Node;

import java.util.UUID;

@Repository
public interface NodeRepository extends JpaRepository<Node, UUID> {
}
