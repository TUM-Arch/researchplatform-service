package de.tum.ar.researchplatform.repository;

import de.tum.ar.researchplatform.model.Field;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by karthik on 9/10/2019
 */

/**
 * Data Repository class used to access Field collection
 */
@RestResource(exported = false)
public interface FieldRepository extends MongoRepository<Field,String> {

    /**
     * Find Field object by English name
     * @param nameEn
     * @return Field object
     */
    public Field findByNameEn(String nameEn);

    /**
     * Find Field object by German name
     * @param nameDe
     * @return Field object
     */
    public Field findByNameDe(String nameDe);
}
