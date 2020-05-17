package de.tum.ar.researchplatform.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by karthik on 9/9/2019
 */

/**
 * Class representing a User object
 */
@Document(collection = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    private String id; // Used by Mongo automatically

    private String name;

    @Indexed(unique = true)
    private String tumId;

    private boolean admin;

    @Setter(AccessLevel.NONE)
    private Date createdAt;

    public User(String name, String tumId, boolean admin) {
        this.createdAt = new Date();
        this.name = name;
        this.tumId = tumId;
        this.admin = admin;
    }
    public User(String tumId) {
        this.createdAt = new Date();
        this.tumId = tumId;
    }
    public User(String tumId, boolean admin) {
        this.createdAt = new Date();
        this.tumId = tumId;
        this.admin = admin;
    }
}
