package site.devdalus.ariadne.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class BasicRepositoryTest {

    @Autowired
    private BasicRepository basicRepository;

    @Test
    public void testBasicRepository() {
        Basic basic = new Basic("basic", 30);
        basic.testSetName("newBasic");

        basicRepository.save(basic);
        Basic foundBasic = basicRepository.findById(basic.getId()).orElse(null);
        assertNotNull(foundBasic);
        assertEquals("newBasic", foundBasic.getName());
    }

}
