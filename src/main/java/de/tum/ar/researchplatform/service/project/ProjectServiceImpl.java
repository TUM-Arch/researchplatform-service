package de.tum.ar.researchplatform.service.project;


import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by karthik on 9/10/2019
 */
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> listAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(String id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public List<Project> findByNameForUser(String userId, String name) {
        List<Project> projects = projectRepository.findByUserId(userId);
        List <Project> projectsFound = projects.stream()
                .filter(project -> project.getName().contains(name))
                .collect(Collectors.toList());
        return projectsFound;
    }

    @Override
    public Project saveOrUpdate(Project project) {
        projectRepository.save(project);
        log.info("Updated Project: " + project);
        return project;
    }

    @Override
    public void delete(Project project) {
        projectRepository.delete(project);
        log.info("Deleted Project: " + project);
    }

    @Override
    public void deleteById(String id) {
        projectRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        if(projectRepository.existsById(id)) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        projectRepository.deleteAll();
        log.info("Deleted All Projects");
    }

    @Override
    public List<Project> findByUserId(String userId) {
        return projectRepository.findByUserId(userId);
    }
}
