package de.tum.ar.researchplatform.model;

/**
 * Created by karthik on 9/10/2019
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class representing a Form object
 */
@Document(collection = "form")
public class Form {

    @Id
    private String id; // Used by Mongo automatically

    private String name;

    private List<String> fieldIds;

    private boolean isActive;

    private Date createdAt;

    /**
     * No arg constructor
     */
    public Form() {
        this.createdAt = new Date();
        this.name = "";
        this.fieldIds = new ArrayList<>();
        this.isActive = false;
    }

    /**
     * Constructor
     * @param name
     * @param fieldIds
     * @param isActive
     */
    public Form(String name, List<String> fieldIds, boolean isActive) {
        this.createdAt = new Date();
        this.name = name;
        this.fieldIds = fieldIds;
        this.isActive = isActive;
    }

    /**
     * Get id for Form object
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get fields associated
     * @return fieldsReferences
     */
    public List<String> getFieldIds() {
        return fieldIds;
    }

    /**
     * Set associated fields
     * @param fieldIds
     */
    public void setFieldIds(List<String> fieldIds) {
        this.fieldIds = fieldIds;
    }

    /**
     * Get if form active
     * @return true for active form
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Set form as active
     * @param active
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Get date of object creation
     * @return date
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fieldIds=" + fieldIds +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                '}';
    }
}
