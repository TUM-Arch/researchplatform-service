package de.tum.ar.researchplatform.service;

import de.tum.ar.researchplatform.model.Field;
import de.tum.ar.researchplatform.repository.FieldRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by karthik on 9/10/2019
 */
@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    private static final Logger logger = LoggerFactory.getLogger(FieldServiceImpl.class);

    @Override
    public Iterable listAll() {
        return fieldRepository.findAll();
    }

    @Override
    public Field findById(String id) {
        return fieldRepository.findById(id).orElse(null);
    }

    @Override
    public Field saveOrUpdate(Field field) {
        fieldRepository.save(field);
        logger.info("Updated Field: " + field);
        return field;
    }

    @Override
    public void delete(Field field) {
        fieldRepository.delete(field);
        logger.info("Deleted Field: " + field);
    }

    @Override
    public void deleteById(String id) {
        fieldRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        if(fieldRepository.existsById(id)) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        fieldRepository.deleteAll();
        logger.info("Deleted All Fields");
    }

    @Override
    public Field findByNameEn(String nameEn) {
        return fieldRepository.findByNameEn(nameEn);
    }

    @Override
    public Field findByNameDe(String nameDe) {
        return fieldRepository.findByNameDe(nameDe);
    }
}
