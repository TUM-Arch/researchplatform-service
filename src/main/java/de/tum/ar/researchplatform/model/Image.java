package de.tum.ar.researchplatform.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by karthik on 4/18/2020
 */
@Document(collection = "image")
@Getter
@Setter
@NoArgsConstructor
public class Image {

    @Id
    private String id;
    private String imageName;
    private Binary image;
}
