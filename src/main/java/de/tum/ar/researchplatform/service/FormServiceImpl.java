package de.tum.ar.researchplatform.service;

import de.tum.ar.researchplatform.model.Form;
import de.tum.ar.researchplatform.repository.FormRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by karthik on 9/10/2019
 */
@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepository;

    private static final Logger logger = LoggerFactory.getLogger(FormServiceImpl.class);

    @Override
    public Iterable listAll() {
        return formRepository.findAll();
    }

    @Override
    public Form findById(String id) {
        return formRepository.findById(id).orElse(null);
    }

    @Override
    public Form findByName(String name) {
        return formRepository.findByName(name);
    }

    @Override
    public Form saveOrUpdate(Form form) {
        formRepository.save(form);
        logger.info("Updated Form: " + form);
        return form;
    }

    @Override
    public void delete(Form form) {
        formRepository.delete(form);
        logger.info("Deleted Form: " + form);
    }

    @Override
    public void deleteById(String id) {
        formRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        if(formRepository.existsById(id)) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        formRepository.deleteAll();
        logger.info("Deleted All Forms");
    }
}
