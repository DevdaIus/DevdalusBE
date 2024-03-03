package site.devdalus.ariadne.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import site.devdalus.ariadne.domain.Role;
import site.devdalus.ariadne.domain.User;
import site.devdalus.ariadne.domain.UserRole;
import site.devdalus.ariadne.repository.mock.MockGenerator;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
class UserRoleRepositoryTest {

    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void save() {
        User user = MockGenerator.getMockUser();
        Role role = MockGenerator.getMockRole();

        userRepository.save(user);
        roleRepository.save(role);

        UserRole userRole = UserRole.builder()
                .role(role)
                .user(user)
                .build();

        userRoleRepository.save(userRole);

        UserRole findUserRole = userRoleRepository.findAll().getFirst();
        assertThat(findUserRole.getRole()).isEqualTo(role);
        assertThat(findUserRole.getUser()).isEqualTo(user);
    }

}