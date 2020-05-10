package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.exception.CustomNotFoundException;
import de.tum.ar.researchplatform.model.Image;
import de.tum.ar.researchplatform.model.Project;
import de.tum.ar.researchplatform.model.response.ImageResponseObject;
import de.tum.ar.researchplatform.service.image.ImageService;
import de.tum.ar.researchplatform.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by karthik on 3/29/2020
 */

@RestController
@RequestMapping(value = "/api")
public class ImageController {

    @Autowired
    private ImageService imageService;
    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/images/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ImageResponseObject getImage(@PathVariable String id) throws CustomNotFoundException {
        Image image = imageService.findById(id);
        return new ImageResponseObject(image);
    }

    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ImageResponseObject addImage(@RequestParam("image") MultipartFile image, @RequestParam("projectId") String projectId)
            throws IOException, CustomNotFoundException {
        Project project = projectService.findById(projectId);
        Image addedImage = imageService.addImage(image);
        project.setImageId(addedImage.getId());
        return new ImageResponseObject(addedImage);
    }

    @PutMapping(value = "/images/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ImageResponseObject updateImage(@PathVariable String id, @RequestParam("image") MultipartFile image, @RequestParam("projectId") String projectId)
            throws IOException, CustomNotFoundException {
        Project project = projectService.findById(projectId);
        Image updatedImage = imageService.updateImage(image, id);
        project.setImageId(updatedImage.getId());
        return new ImageResponseObject(updatedImage);
    }

    @DeleteMapping(value = "/images/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteImage(@PathVariable String id, @RequestParam("projectId") String projectId) throws CustomNotFoundException {
        imageService.deleteById(id);
        Project project = projectService.findById(projectId);
        project.setImageId(null);
    }
}
