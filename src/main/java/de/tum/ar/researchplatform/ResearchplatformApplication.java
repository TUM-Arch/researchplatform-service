package de.tum.ar.researchplatform;

import de.tum.ar.researchplatform.model.Form;
import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.model.User;
import de.tum.ar.researchplatform.service.FormServiceImpl;
import de.tum.ar.researchplatform.service.ProjectServiceImpl;
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
    @Autowired
    private FormServiceImpl formService;
    @Autowired
    private ProjectServiceImpl projectService;

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
        FormServiceImpl formService = context.getBean(FormServiceImpl.class);
        ProjectServiceImpl projectService = context.getBean(ProjectServiceImpl.class);

        //Delete All
        userService.deleteAll();
        formService.deleteAll();
        projectService.deleteAll();

        //Create and save
        int i = 0;
        while(i < 10000) {

            User user = new User("name", "tumid" + i, true);
            userService.saveOrUpdate(user);

            Form form = new Form();
            formService.saveOrUpdate(form);

            Project project = new Project();
            projectService.saveOrUpdate(project);
            i++;
        }

        //CleanUp
        /*userService.deleteAll();
        formService.deleteAll();
        projectService.deleteAll();*/

        context.close();
    }
}
