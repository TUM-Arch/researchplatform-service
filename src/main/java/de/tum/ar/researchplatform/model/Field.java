package de.tum.ar.researchplatform.model;

/**
 * Created by karthik on 9/10/2019
 */

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Class representing a FormField object
 */
@Document(collection = "field")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Field {

    @Id
    private String id; // Used by Mongo automatically

    private String nameEn;
    private String nameDe;

    private String valueEn;
    private String valueDe;

    private String description;

    private boolean required;
    private boolean active;
    @Setter(AccessLevel.NONE)
    private Date createdAt;

    public Field(String nameEn, String nameDe, String valueEn, String valueDe, String description, boolean required, boolean active) {
        this.createdAt = new Date();
        this.nameEn = nameEn;
        this.nameDe = nameDe;
        this.valueEn = valueEn;
        this.valueDe = valueDe;
        this.description = description;
        this.required = required;
        this.active = active;
    }
}
