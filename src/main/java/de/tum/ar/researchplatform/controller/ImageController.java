package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.exception.CustomNotFoundException;
import de.tum.ar.researchplatform.model.Image;
import de.tum.ar.researchplatform.service.image.ImageService;
import de.tum.ar.researchplatform.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by karthik on 3/29/2020
 */

@RestController
@RequestMapping(value = "/api")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/images/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getImage(@PathVariable String id) throws CustomNotFoundException {
        Image image = imageService.findById(id);
        if(image != null && image.getImage() != null) {
            return Base64.getEncoder().encodeToString(image.getImage().getData());
        }
        return "null";
    }

    @PostMapping(value = "/images")
    public Image addImage(@RequestParam("image") MultipartFile image)
            throws IOException {
        return imageService.addImage(image);
    }

    @PutMapping(value = "/images/{id}")
    public Image updateImage(@PathVariable String id, @RequestParam("image") MultipartFile image)
            throws IOException, CustomNotFoundException {
        return imageService.updateImage(image, id);
    }

    @DeleteMapping(value = "/images/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteImage(@PathVariable String id)
    {
        imageService.deleteById(id);
    }
}
