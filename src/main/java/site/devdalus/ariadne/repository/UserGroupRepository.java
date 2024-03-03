package site.devdalus.ariadne.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.devdalus.ariadne.domain.Board;
import site.devdalus.ariadne.domain.UserGroup;

import java.util.UUID;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, UUID> {
}
