package de.tum.ar.researchplatform.service.project;

import de.tum.ar.researchplatform.exception.CustomNotFoundException;
import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.util.Constants;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static de.tum.ar.researchplatform.util.Constants.ProjectStatus.*;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by karthik on 3/29/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceImplIntegrationTest {

    @Autowired
    private ProjectService projectService;

    @After
    public void breakdown() {
        projectService.deleteAll();
    }

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

    @Test
    public void testFilterByStatus() {
        Project projectAbc = new Project();
        projectAbc.setUserId("user");
        projectAbc.setName("Abc");
        projectAbc.setStatus(Constants.ProjectStatus.NOTSUBMITTED);
        projectService.saveOrUpdate(projectAbc);

        Project projectXyz = new Project();
        projectXyz.setUserId("user");
        projectXyz.setName("Abc");
        projectXyz.setStatus(SUBMITTED);
        projectService.saveOrUpdate(projectXyz);

        List<Project> allProjects = projectService.findByUserId("user");
        List<Project> projects = projectService.filterByStatus(allProjects, SUBMITTED);
        for (Project project : projects) {
            assertThat(project.getStatus()).isEqualTo(SUBMITTED);
        }
    }

    @Test
    public void testFilterBySubmittedApprovedAndRejected() {
        Project projectAbc = new Project();
        projectAbc.setStatus(Constants.ProjectStatus.NOTSUBMITTED);
        projectService.saveOrUpdate(projectAbc);

        Project projectXyz = new Project();
        projectXyz.setStatus(SUBMITTED);
        projectService.saveOrUpdate(projectXyz);

        Project projectIjk = new Project();
        projectIjk.setStatus(Constants.ProjectStatus.APPROVED);
        projectService.saveOrUpdate(projectIjk);

        Project projectPqr = new Project();
        projectPqr.setStatus(REJECTED);
        projectService.saveOrUpdate(projectPqr);

        List<Project> allProjects = projectService.listAll();
        List<Project> projects = projectService.filterBySubmittedApprovedAndRejected(allProjects);
        for (Project project : projects) {
            assertThat(project.getStatus()).isIn(SUBMITTED, APPROVED, REJECTED);
        }
    }

    @Test
    public void testAdvanceWorkflowToSubmitted() throws CustomNotFoundException {
        Project newProject = new Project();
        newProject = projectService.saveOrUpdate(newProject);
        Project submittedProject = projectService.advanceWorkflow(newProject.getId());
        assertThat(submittedProject.getStatus()).isEqualTo(SUBMITTED);
    }

    @Test
    public void testAdvanceWorkflowToApproved() throws CustomNotFoundException {
        Project newProject = new Project();
        newProject = projectService.saveOrUpdate(newProject);
        // Advance to Submitted
        Project submittedProject = projectService.advanceWorkflow(newProject.getId());

        // Advance to Approved
        Project approvedProject = projectService.advanceWorkflow(submittedProject.getId());
        assertThat(approvedProject.getStatus()).isEqualTo(Constants.ProjectStatus.APPROVED);
    }

    @Test
    public void testRejectWorkflowToRejected() throws CustomNotFoundException {
        Project newProject = new Project();
        newProject = projectService.saveOrUpdate(newProject);
        // Advance to Submitted
        Project submittedProject = projectService.advanceWorkflow(newProject.getId());

        // Reject to Rejected
        Project rejectedProject = projectService.rejectWorkflow(submittedProject.getId(), "Rejection Text");
        assertThat(rejectedProject.getStatus()).isEqualTo(Constants.ProjectStatus.REJECTED);
    }
}
