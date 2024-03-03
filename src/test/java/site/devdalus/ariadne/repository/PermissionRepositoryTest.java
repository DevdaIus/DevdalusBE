package site.devdalus.ariadne.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import site.devdalus.ariadne.domain.Permission;
import site.devdalus.ariadne.repository.mock.MockGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PermissionRepositoryTest {

    @Autowired
    PermissionRepository permissionRepository;

    private Permission permission;

    @BeforeEach
    public void beforeEach() {
        permission = MockGenerator.getMockPermission();
        permissionRepository.save(permission);
    }

    @Test
    public void save() {
        Permission findPermission = permissionRepository.findAll().getFirst();
        assertThat(findPermission.getPermissionName()).isEqualTo(permission.getPermissionName());
    }
}