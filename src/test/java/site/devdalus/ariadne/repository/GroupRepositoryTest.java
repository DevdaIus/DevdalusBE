package site.devdalus.ariadne.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    private Group group;

    @BeforeEach
    public void beforeEach() {
        group = MockGenerator.getMockGroup();
        groupRepository.save(group);
    }

    @Test
    public void save() {
        Group findGroup = groupRepository.findAll().getFirst();
        assertThat(findGroup.getGroupName()).isEqualTo(group.getGroupName());
    }

    @Test
    public void update() {
        Group findGroup = groupRepository.findAll().getFirst();
        findGroup.setGroupName("newname");
        groupRepository.save(findGroup);
        Group setGroup = groupRepository.findAll().getFirst();
        assertThat(setGroup.getGroupName()).isEqualTo("newname");
    }

}