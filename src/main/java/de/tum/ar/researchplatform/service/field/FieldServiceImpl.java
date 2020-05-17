package de.tum.ar.researchplatform.service.field;

import de.tum.ar.researchplatform.exception.CustomNotFoundException;
import de.tum.ar.researchplatform.model.Field;
import de.tum.ar.researchplatform.repository.FieldRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.tum.ar.researchplatform.util.Constants.FIELD_NOT_FOUND_MSG;

/**
 * Created by karthik on 9/10/2019
 */
@Service
@Slf4j
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    @Override
    public List<Field> listAll() {
        return fieldRepository.findAll();
    }

    @Override
    public Field findById(String id) throws CustomNotFoundException {
        Field  field = fieldRepository.findById(id).orElse(null);
        if(field == null) {
            throw new CustomNotFoundException(FIELD_NOT_FOUND_MSG);
        }
        return field;
    }

    @Override
    public Field saveOrUpdate(Field field) {
        fieldRepository.save(field);
        log.info("Updated Field: " + field);
        return field;
    }

    @Override
    public void delete(Field field) {
        fieldRepository.delete(field);
        log.info("Deleted Field: " + field);
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
        log.info("Deleted All Fields");
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
