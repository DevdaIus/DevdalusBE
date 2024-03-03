package site.devdalus.ariadne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.devdalus.ariadne.domain.Board;
import site.devdalus.ariadne.domain.Group;

import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {
}
