package de.tum.ar.researchplatform.repository;

import de.tum.ar.researchplatform.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by karthik on 4/18/2020
 */
public interface ImageRepository extends MongoRepository<Image, String> {
}
