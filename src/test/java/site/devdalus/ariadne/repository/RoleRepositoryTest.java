package site.devdalus.ariadne.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import site.devdalus.ariadne.domain.Role;
import site.devdalus.ariadne.repository.mock.MockGenerator;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class RoleRepositoryTest {


    @Autowired
    RoleRepository roleRepository;

    private Role role;

    @BeforeEach
    public void beforeEach() {
        role = MockGenerator.getMockRole();
        roleRepository.save(role);
    }

    @Test
    public void save() {
        Role findRole = roleRepository.findAll().getFirst();
        assertThat(findRole.getRoleName()).isEqualTo(role.getRoleName());
    }


}