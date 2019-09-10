package de.tum.ar.researchplatform.service;


import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by karthik on 9/10/2019
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Override
    public Iterable listAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(String id) {
        return projectRepository.findById(id).orElse(null);
    }

    @Override
    public Project saveOrUpdate(Project project) {
        projectRepository.save(project);
        logger.info("Updated Project: " + project);
        return project;
    }

    @Override
    public void delete(Project project) {
        projectRepository.delete(project);
        logger.info("Deleted Project: " + project);
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
        logger.info("Deleted All Projects");
    }

    @Override
    public Project findByUserId(String userId) {
        return projectRepository.findByUserId(userId);
    }
}
