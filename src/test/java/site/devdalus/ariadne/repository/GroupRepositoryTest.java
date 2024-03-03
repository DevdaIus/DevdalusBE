package site.devdalus.ariadne.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import site.devdalus.ariadne.domain.Group;
import site.devdalus.ariadne.repository.mock.MockGenerator;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class GroupRepositoryTest {

    @Autowired
    GroupRepository groupRepository;

    @Test
    public void save() {
        Group group = MockGenerator.getMockGroup();
        groupRepository.save(group);

        Group findGroup = groupRepository.findAll().getFirst();

        assertThat(findGroup.getGroupName()).isEqualTo(group.getGroupName());
    }

}