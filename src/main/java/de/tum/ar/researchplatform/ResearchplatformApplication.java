package de.tum.ar.researchplatform;

import de.tum.ar.researchplatform.model.Form;
import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.model.User;
import de.tum.ar.researchplatform.service.FormServiceImpl;
import de.tum.ar.researchplatform.service.ProjectServiceImpl;
import de.tum.ar.researchplatform.service.UserServiceImpl;
import de.tum.ar.researchplatform.util.Constants;
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
        while(i < 4) {

            User user = new User("name", "tumid" + i, true);
            userService.saveOrUpdate(user);

            Form form = new Form();
            formService.saveOrUpdate(form);

            Project project = new Project("1", "tempuser" + i, Constants.ProjectStatus.SUBMITTED, "Project" + i, "Chair of architecture" + i, "description" + i);
            projectService.saveOrUpdate(project);
            i++;
        }
        i = 0;
        while(i < 4) {

            Project project = new Project("2", "tempuser" + i, Constants.ProjectStatus.SUBMITTED, "Project" + i, "Chair of architecture" + i, "description" + i);
            project.setYearOfCreation(2015 + i);
            projectService.saveOrUpdate(project);
            i++;
        }
        Project project = new Project("2", "tempuser", Constants.ProjectStatus.SUBMITTED, "2nd SKIN Scaler", "Chair for building technology", "The EU needs a zero-energy (ZE) refurbishment rate of around 3% to achieve its 2050 targets for a low carbon urban environment. The current refurbishment rate is far too low, despite all the policy measures over the past years. Although technically feasible, ZE-renovation projects developed in recent years are still too expensive, often suffer from disappointed inhabitants and unexpected higher energy-use.");
        project.setYearOfCreation(2020);
        projectService.saveOrUpdate(project);


        //CleanUp
        /*userService.deleteAll();
        formService.deleteAll();
        projectService.deleteAll();*/

        context.close();
    }
}
