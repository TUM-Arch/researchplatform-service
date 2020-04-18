package de.tum.ar.researchplatform.service.image;

import de.tum.ar.researchplatform.model.Image;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by karthik on 4/18/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceImplIntegrationTest {
    @Autowired
    private ImageService imageService;

    @After
    public void breakdown() {
        imageService.deleteAll();
    }

    // Base Test
    @Test
    public void testSaveOrUpdate() {
        Image image = new Image();
        image.setImage(null);
        Image resultImage = imageService.saveOrUpdate(image);
        assertThat(resultImage.getId()).isNotNull();
    }
}
