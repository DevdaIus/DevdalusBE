package site.devdalus.ariadne.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import site.devdalus.ariadne.constant.LoginType;
import site.devdalus.ariadne.domain.User;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User getMockUser() {
        return User.builder()
                .id("id")
                .password("password")
                .nickname("nickname")
                .loginType(LoginType.DEFAULT)
                .build();
    }

    @Test
    public void save() {

        User user = getMockUser();
        userRepository.save(user);
        User findUser = userRepository.findAll().getFirst();

        assertThat(user.getUserId()).isEqualTo(findUser.getUserId());
        assertThat(user.getPassword()).isEqualTo(findUser.getPassword());
        assertThat(user.getNickname()).isEqualTo(findUser.getNickname());

    }

    @Test
    public void update() {

        User user = getMockUser();
        userRepository.save(user);

        User findUser = userRepository.findById(user.getUserId()).orElseThrow();
        findUser.setNickname("newNickname");

        User setUser = userRepository.findById(user.getUserId()).orElseThrow();
        assertThat(setUser.getNickname()).isEqualTo("newNickname");
    }
    
}