package site.devdalus.ariadne.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import site.devdalus.ariadne.domain.Group;
import site.devdalus.ariadne.domain.User;
import site.devdalus.ariadne.domain.UserGroup;
import site.devdalus.ariadne.repository.mock.MockGenerator;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
class UserGroupRepositoryTest {

    @Autowired
    UserGroupRepository userGroupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;

    @Test
    public void save() {
        User user = MockGenerator.getMockUser();
        Group group = MockGenerator.getMockGroup();
        UserGroup userGroup = UserGroup.builder()
                .user(user)
                .group(group)
                .build();
        userRepository.save(user);
        groupRepository.save(group);
        userGroupRepository.save(userGroup);

        UserGroup findUserGroup = userGroupRepository.findAll().getFirst();
        assertThat(findUserGroup.getUser()).isEqualTo(user);
        assertThat(findUserGroup.getGroup()).isEqualTo(group);
    }


}