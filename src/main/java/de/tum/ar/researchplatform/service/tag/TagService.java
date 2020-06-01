package de.tum.ar.researchplatform.service.tag;

import de.tum.ar.researchplatform.exception.CustomNotFoundException;
import de.tum.ar.researchplatform.model.Tag;

import java.util.List;

/**
 * Created by raymond on 5/31/2020
 */

public interface TagService {
    /**
     * List all Tag objects
     * @return All Tag objects
     */
    List<Tag> listAll();

    /**
     * Find Tag object by id
     * @param id
     * @return Tag object found
     */
    Tag findById(String id) throws CustomNotFoundException;

    /**
     * Find Tag object by name
     * @param name
     * @return Tag object found
     */
    Tag findByName(String name);

    /**
     * Save or Update Field object
     * @param tag
     * @return Tag object saved or updated
     */
    Tag saveOrUpdate(Tag tag);

    /**
     * Save or Update Tag objects
     * @param tags
     * @return List of tag objects saved or updated
     */
    List<Tag> saveOrUpdateTags(List<String> tags);

    /**
     * Delete Tag object
     * @param tag
     */
    void delete(Tag tag);

    /**
     * Delete Tag object by id
     * @param id
     */
    void deleteById(String id);

    /**
     * Delete all Field objects
     */
    void deleteAll();
}
