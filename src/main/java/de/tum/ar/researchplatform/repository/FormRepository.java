package de.tum.ar.researchplatform.repository;

import de.tum.ar.researchplatform.model.Form;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by karthik on 9/10/2019
 */

/**
 * Data Repository class used to access Form collection
 */
@RestResource(exported = false)
public interface FormRepository extends MongoRepository<Form,String> {

    /**
     * Find Form object by name
     * @param name
     * @return Form object
     */
    public Form findByName(String name);
}
