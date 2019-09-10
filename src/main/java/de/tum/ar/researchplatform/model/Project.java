package de.tum.ar.researchplatform.model;

/**
 * Created by karthik on 9/10/2019
 */

import de.tum.ar.researchplatform.util.Constants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Class representing a Project object
 */
@Document(collection = "project")
public class Project {

    @Id
    private String id; // Used by Mongo automatically

    private String formId;

    private String userId;

    private Constants.ProjectStatus status;

    private boolean isPrivate;

    private Date createdAt;

    /**
     * No arg constructor
     */
    public Project() {
        this.createdAt = new Date();
        this.formId = "";
        this.userId = "";
        this.status = Constants.ProjectStatus.NOTSUBMITTED;
        this.isPrivate = false;
    }

    /**
     * Constructor
     * @param formId
     * @param userId
     * @param status
     * @param isPrivate
     */
    public Project(String formId, String userId, Constants.ProjectStatus status, boolean isPrivate) {
        this.createdAt = new Date();
        this.formId = formId;
        this.userId = userId;
        this.status = status;
        this.isPrivate = isPrivate;
    }

    /**
     * Get id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Get associated form reference
     * @return form reference id
     */
    public String getFormId() {
        return formId;
    }

    /**
     * Set form reference
     * @param formId
     */
    public void setFormId(String formId) {
        this.formId = formId;
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
     * Getter for if project is private
     * @return true if project private
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * Set project as private
     * @param aPrivate
     */
    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
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
        return "Project{" +
                "id='" + id + '\'' +
                ", formId='" + formId + '\'' +
                ", userId='" + userId + '\'' +
                ", status=" + status +
                ", isPrivate=" + isPrivate +
                ", createdAt=" + createdAt +
                '}';
    }
}
