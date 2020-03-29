package de.tum.ar.researchplatform.service.field;

import de.tum.ar.researchplatform.model.Field;
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
public class FieldServiceImplIntegrationTest {

    @Autowired
    private FieldService fieldService;

    // Base Test
    @Test
    public void testSaveOrUpdate() {
        Field field = new Field();
        field.setDescription("Test");
        Field resultField = fieldService.saveOrUpdate(field);
        assertThat(resultField.getDescription()).isEqualTo("Test");
    }

    @Test
    public void testListAllActive() {
        // Create two fields, one active, one inactive
        Field activeField = new Field();
        activeField.setActive(true);
        fieldService.saveOrUpdate(activeField);

        Field inactiveField = new Field();
        fieldService.saveOrUpdate(inactiveField);

        List<Field> resultFields = fieldService.listAllActive();
        for(Field field : resultFields) {
            assertThat(field.isActive()).isEqualTo(true);
        }

    }
}
