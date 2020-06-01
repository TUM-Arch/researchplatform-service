package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.component.security.HasAdminRole;
import de.tum.ar.researchplatform.component.security.HasUserRole;
import de.tum.ar.researchplatform.exception.CustomNotFoundException;
import de.tum.ar.researchplatform.model.Tag;
import de.tum.ar.researchplatform.model.request.TagsRequestObject;
import de.tum.ar.researchplatform.model.response.TagsResponseObject;
import de.tum.ar.researchplatform.service.tag.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raymond on 5/31/2020
 */

@RestController
@RequestMapping(value = "/api")
public class TagController {
    @Autowired
    private TagServiceImpl tagService;

    /**
     * Endpoint to get all Tags
     * @return ProjectsResponseObject as list of Projects
     */
    @GetMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    @HasUserRole
    public TagsResponseObject getAllTags() {
        TagsResponseObject tagsResponseObject = new TagsResponseObject();
        List<Tag> tagList = tagService.listAll();
        tagsResponseObject.setTagsList(tagList);
        return tagsResponseObject;
    }

    /**
     * Endpoint to add a List of Tags
     * @return a List of Tags
     */
    @PostMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    @HasUserRole
    public TagsResponseObject addTags(@Valid @RequestBody TagsRequestObject tags) {
        TagsResponseObject tagsResponseObject = new TagsResponseObject();
        List<Tag> tagList = new ArrayList<>();
        tagList = tagService.saveOrUpdateTags(tags.getTags());
        tagsResponseObject.setTagsList(tagList);
        return tagsResponseObject;
    }

    /**
     * Endpoint to update a Tag
     * @return a single Tag
     */
    @PutMapping(value = "/tags/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @HasAdminRole
    public Tag updateTag(@PathVariable String id, @RequestParam String tagName) throws CustomNotFoundException {
        Tag tag = tagService.findById(id);
        return tagService.saveOrUpdate(tag);
    }

    /**
     * Endpoint to delete all Tags
     */
    @DeleteMapping(value = "/tags")
    @HasAdminRole
    public void deleteAllTags() {
        tagService.deleteAll();
    }

    /**
     * Endpoint to delete a Tag by id
     */
    @DeleteMapping(value = "/tags/{id}")
    @HasAdminRole
    public void deleteTagById(@PathVariable String id) {
        tagService.deleteById(id);
    }
}
