package de.tum.ar.researchplatform.model;

/**
 * Created by raymond on 5/31/2020
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class representing a Tag object
 */
@Document(collection = "tag")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Tag {
    @Id
    private String id;
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
