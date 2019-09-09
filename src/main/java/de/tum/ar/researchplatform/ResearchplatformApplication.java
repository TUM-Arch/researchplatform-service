package de.tum.ar.researchplatform;

import de.tum.ar.researchplatform.model.User;
import de.tum.ar.researchplatform.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ResearchplatformApplication {

    @Autowired
    private UserServiceImpl userService;

    private static final Logger logger = LoggerFactory.getLogger(ResearchplatformApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ResearchplatformApplication.class, args);
		testDBCode();
	}

    public static void testDBCode() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("de.tum.ar.researchplatform");
        context.refresh();

        //Services
        UserServiceImpl userService = context.getBean(UserServiceImpl.class);

        //Delete All
        userService.deleteAll();

        //Create and save
        User user = new User("name", "tumid", true);
        userService.saveOrUpdate(user);

        //Others
        user = userService.findByName("name");
        logger.info(user.toString());

        Iterable<User> userIterable = userService.listAll();
        for (User userInList: userIterable) {
            logger.info(userInList.toString());
        }

        userService.delete(user);

        context.close();
    }

}
