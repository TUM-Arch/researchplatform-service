package de.tum.ar.researchplatform.service.field;

import de.tum.ar.researchplatform.model.Field;
import de.tum.ar.researchplatform.repository.FieldRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<Field> listAllActive() {
        List<Field> fields = new ArrayList<>(fieldRepository.findAll());
        List<Field> activeFields = new ArrayList<>();
         for(Field field : fields) {
             if(field.isActive()) {
                 activeFields.add(field);
             }
         }
         return activeFields;
    }

    @Override
    public Field findById(String id) {
        return fieldRepository.findById(id).orElse(null);
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
