package de.tum.ar.researchplatform.service.tag;

import de.tum.ar.researchplatform.exception.CustomNotFoundException;
import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.model.Tag;
import de.tum.ar.researchplatform.service.project.ProjectService;
import de.tum.ar.researchplatform.util.Constants;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.tum.ar.researchplatform.util.Constants.ProjectStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by raymond on 6/1/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagServiceIntegrationTest {
    @Autowired
    private TagService tagService;

    @After
    public void breakdown() {
        tagService.deleteAll();
    }

    // Base Test
    @Test
    public void testSaveOrUpdate() {
        Tag tag = new Tag();
        tag.setName("Test Tag");
        Tag resultTag = tagService.saveOrUpdate(tag);
        assertThat(resultTag.getName()).isEqualTo("Test Tag");
    }

    @Test
    public void testSaveOrUpdateTags() {
        List<String> tags = new ArrayList<String>(Arrays.asList("Tag1", "Tag2"));
        List<Tag> resultTag = tagService.saveOrUpdateTags(tags);
        assertThat(resultTag.size()).isEqualTo(2);

        List<String> existingTag = new ArrayList<String>(Arrays.asList("Tag1"));
        tagService.saveOrUpdateTags(existingTag);
        assertThat(tagService.listAll().size()).isEqualTo(2);
    }

    @Test
    public void testFindByName() {
        Tag tag = new Tag();
        tag.setName("Test Tag");
        tagService.saveOrUpdate(tag);

        Tag foundTag = tagService.findByName("Test Tag");
        assertThat(foundTag.getName()).isEqualTo("Test Tag");
    }
}
