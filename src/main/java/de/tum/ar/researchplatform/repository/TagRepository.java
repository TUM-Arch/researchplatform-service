package de.tum.ar.researchplatform.repository;

/**
 * Created by raymond on 5/31/2020
 */

import de.tum.ar.researchplatform.model.Image;
import de.tum.ar.researchplatform.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Data Repository class used to access Tag collection
 */
public interface TagRepository extends MongoRepository<Tag, String> {

    /**
     * Find Tag object by name
     * @param name
     * @return Tag object
     */
    Tag findByName(String name);
}
