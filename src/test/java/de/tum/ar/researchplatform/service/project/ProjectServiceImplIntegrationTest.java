package de.tum.ar.researchplatform.service.project;

import de.tum.ar.researchplatform.model.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by karthik on 3/29/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceImplIntegrationTest {

    @Autowired
    private ProjectService projectService;

    // Base Test
    @Test
    public void testSaveOrUpdate() {
        Project project = new Project();
        project.setName("Test");
        Project resultProject = projectService.saveOrUpdate(project);
        assertThat(resultProject.getName()).isEqualTo("Test");
    }

    @Test
    public void testFindByNameForUser() {
        Project projectAbc = new Project();
        projectAbc.setUserId("user");
        projectAbc.setName("Abc");
        projectService.saveOrUpdate(projectAbc);

        Project projectXyz = new Project();
        projectXyz.setUserId("user");
        projectXyz.setName("Xyz");
        projectService.saveOrUpdate(projectXyz);

        List<Project> projects = projectService.findByNameForUser("user", "Abc");
        for(Project project : projects) {
            assertThat(project.getName()).isEqualTo("Abc");
        }
    }
}
