package site.devdalus.ariadne.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestEntityRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TestEntityRepository repository;

    @Test
    public void testSaveAndFind() {
        TestEntity entity = new TestEntity();
        entity.setName("Test Name");
        entity = entityManager.persistAndFlush(entity);

        TestEntity foundEntity = repository.findById(entity.getId()).orElse(null);
        assertThat(foundEntity).isNotNull();
        assertThat(foundEntity.getName()).isEqualTo(entity.getName());

    }


}
