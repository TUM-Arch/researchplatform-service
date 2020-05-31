package de.tum.ar.researchplatform.model;

/**
 * Created by karthik on 9/10/2019
 */

import de.tum.ar.researchplatform.util.Constants;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class representing a Project object
 */
@Document(collection = "project")
@Getter
@Setter
@ToString
public class Project {

    @Id
    private String id; // Used by Mongo automatically

    private String name;
    private String chairName;
    private String description;

    private String imageId;

    private String userId;

    private List<String> tags;
    private List<Field> fields;
    private Constants.ProjectStatus status;
    private String rejectionText;

    @Setter(AccessLevel.NONE)
    private Date createdAt;
    @Setter(AccessLevel.NONE)
    private int yearOfCreation;

    public Project() {
        this.createdAt = new Date();
        this.userId = "";
        this.status = Constants.ProjectStatus.NOTSUBMITTED;
        this.name = "";
        this.chairName = "";
        this.description = "";
        this.imageId = "";
        this.tags = new ArrayList<>();
        this.fields = new ArrayList<>();
        this.rejectionText = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.createdAt);
        this.yearOfCreation = calendar.get(Calendar.YEAR);
    }

    public Project(String userId, String name, String chairName, String description, String imageId, List<String> tags, List<Field> fields) {
        this.createdAt = new Date();
        this.userId = userId;
        this.status = Constants.ProjectStatus.NOTSUBMITTED;
        this.name = name;
        this.chairName = chairName;
        this.description = description;
        this.imageId = imageId;
        this.tags = tags;
        this.fields = fields;
        this.rejectionText = "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.createdAt);
        this.yearOfCreation = calendar.get(Calendar.YEAR);
    }

    /**
     * Set year of object creation
     */
    public void setYearOfCreation() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.createdAt);
        this.yearOfCreation = calendar.get(Calendar.YEAR);
    }

    /**
     * Set year of object creation
     * @param year
     */
    public void setYearOfCreation(int year) {
        this.yearOfCreation = year;
    }
}
