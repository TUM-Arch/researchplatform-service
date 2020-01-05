package de.tum.ar.researchplatform.model;

/**
 * Created by karthik on 9/10/2019
 */

import de.tum.ar.researchplatform.util.Constants;
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
public class Project {

    @Id
    private String id; // Used by Mongo automatically

    private String name;
    private String chairName;
    private String description;
    private String userId;
    private List<String> tags;

    private List<Field> fields;

    private Constants.ProjectStatus status;

    private Date createdAt;

    private int yearOfCreation;

    /**
     * No arg constructor
     */
    public Project() {
        this.createdAt = new Date();
        this.userId = "";
        this.status = Constants.ProjectStatus.NOTSUBMITTED;
        this.name = "";
        this.chairName = "";
        this.description = "";
        this.tags = new ArrayList<>();
        this.fields = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.createdAt);
        this.yearOfCreation = calendar.get(Calendar.YEAR);
    }

    /**
     * Constructor
     * @param userId
     * @param status
     * @param name
     * @param chairName
     * @param description
     * @param tags
     * @param fields
     */
    public Project(String userId, Constants.ProjectStatus status, String name, String chairName, String description, List<String> tags, List<Field> fields) {
        this.createdAt = new Date();
        this.userId = userId;
        this.status = status;
        this.name = name;
        this.chairName = chairName;
        this.description = description;
        this.tags = tags;
        this.fields = fields;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.createdAt);
        this.yearOfCreation = calendar.get(Calendar.YEAR);
    }

    /**
     * Get id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Get associated user reference
     * @return user reference id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set user reference
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Get all tags associated with project
     * @return tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * Set tags associated with project
     * @param tags
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Get all form fields for project
     * @return fields
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * Set form fields for project
     * @param fields
     */
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    /**
     * Get project name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set project name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get chair name
     * @return name
     */
    public String getChairName() {
        return chairName;
    }

    /**
     * Set chair name
     * @param chairName
     */
    public void setChairName(String chairName) {
        this.chairName = chairName;
    }

    /**
     * Get description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get project status
     * @return project status
     */
    public Constants.ProjectStatus getStatus() {
        return status;
    }

    /**
     * Set project status
     * @param status
     */
    public void setStatus(Constants.ProjectStatus status) {
        this.status = status;
    }

    /**
     * Get date of object creation
     * @return date
     */
    public Date getCreatedAt() {
        return createdAt;
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

    /**
     * Get year of object creation
     * @return year
     */
    public int getYearOfCreation() {
        return yearOfCreation;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", chairName='" + chairName + '\'' +
                ", description='" + description + '\'' +
                ", userId='" + userId + '\'' +
                ", tags='" + tags + '\'' +
                ", fields='" + fields + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", yearOfCreation=" + yearOfCreation +
                '}';
    }
}
