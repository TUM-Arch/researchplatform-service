package de.tum.ar.researchplatform.service.user;

import de.tum.ar.researchplatform.model.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by karthik on 3/29/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplIntegrationTest {

    @Autowired
    private UserService userService;

    @After
    public void breakdown() {
        userService.deleteAll();
    }

    // Base Test
    @Test
    public void testSaveOrUpdate() {
        User user = new User();
        user.setName("Test");
        User resultUser = userService.saveOrUpdate(user);
        assertThat(resultUser.getName()).isEqualTo("Test");
    }
}
