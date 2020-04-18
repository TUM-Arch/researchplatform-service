package de.tum.ar.researchplatform.service.image;

import de.tum.ar.researchplatform.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by karthik on 3/29/2020
 */
public interface ImageService {

    /**
     * Find Image object by id
     * @param id
     * @return Image object found
     */
    Image findById(String id);

    /**
     * Save or Update Image object
     * @param image
     * @return Image object saved or updated
     */
    Image saveOrUpdate(Image image);

    /**
     * Delete Image object
     * @param image
     */
    void delete(Image image);

    /**
     * Delete Image object by id
     * @param id
     */
    void deleteById(String id);

    /**
     * Delete all Image objects
     */
    void deleteAll();

    /**
     * Check if Image object exists by id
     * @param id
     * @return true if it exists
     */
    boolean existsById(String id);

    /**
     * Add image to DB
     * @param file
     * @return ImageId
     */
    Image addImage(MultipartFile file) throws IOException;

    /**
     * Update image in DB
     * @param file
     * @param id
     * @return updated ImageId
     */
    Image updateImage(MultipartFile file, String id) throws IOException;
}
