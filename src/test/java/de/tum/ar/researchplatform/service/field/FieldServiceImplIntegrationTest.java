package de.tum.ar.researchplatform.service.field;

import de.tum.ar.researchplatform.model.Field;
import org.junit.After;
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

    @After
    public void breakdown() {
        fieldService.deleteAll();
    }

    // Base Test
    @Test
    public void testSaveOrUpdate() {
        Field field = new Field();
        field.setDescription("Test");
        Field resultField = fieldService.saveOrUpdate(field);
        assertThat(resultField.getDescription()).isEqualTo("Test");
    }
}
