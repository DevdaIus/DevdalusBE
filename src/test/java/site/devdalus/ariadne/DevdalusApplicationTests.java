package site.devdalus.ariadne;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class DevdalusApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("test");
		Assertions.assertThat(1).isEqualTo(0);
	}
}
