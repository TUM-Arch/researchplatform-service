package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.exception.CustomNotFoundException;
import de.tum.ar.researchplatform.model.Image;
import de.tum.ar.researchplatform.model.response.ImageResponseObject;
import de.tum.ar.researchplatform.service.image.ImageService;
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

    @GetMapping(value = "/images/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ImageResponseObject getImage(@PathVariable String id) throws CustomNotFoundException {
        Image image = imageService.findById(id);
        return new ImageResponseObject(image);
    }

    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ImageResponseObject addImage(@RequestParam("image") MultipartFile image)
            throws IOException {
        return new ImageResponseObject(imageService.addImage(image));
    }

    @PutMapping(value = "/images/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ImageResponseObject updateImage(@PathVariable String id, @RequestParam("image") MultipartFile image)
            throws IOException, CustomNotFoundException {
        return new ImageResponseObject(imageService.updateImage(image, id));
    }

    @DeleteMapping(value = "/images/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteImage(@PathVariable String id)
    {
        imageService.deleteById(id);
    }
}
