package de.tum.ar.researchplatform.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by karthik on 9/9/2019
 */

/**
 * Class representing a User object
 */
@Document(collection = "user")
public class User {

    @Id
    private String id; // Used by Mongo automatically

    private String name;

    @Indexed(unique = true)
    private String tumId;

    private List<String> projectIds;

    private boolean isAdmin;

    private Date createdAt;

    /**
     * No arg constructor
     */
    public User() {
        this.createdAt = new Date();
        this.name = "";
        this.tumId = "";
        this.projectIds = new ArrayList<>();
        this.isAdmin = false;
    }

    /**
     * Constructor
     * @param name
     * @param tumId
     * @param isAdmin
     */
    public User(String name, String tumId, boolean isAdmin) {
        this.createdAt = new Date();
        this.name = name;
        this.tumId = tumId;
        this.projectIds = new ArrayList<>();
        this.isAdmin = isAdmin;
    }

    /**
     * Constructor
     * @param name
     * @param tumId
     * @param projectIds
     * @param isAdmin
     */
    public User(String name, String tumId, List<String> projectIds, boolean isAdmin) {
        this.createdAt = new Date();
        this.name = name;
        this.tumId = tumId;
        this.projectIds = projectIds;
        this.isAdmin = isAdmin;
    }

    /**
     * Get User id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Get User name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set User name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get TUM ID
     * @return TUM ID
     */
    public String getTumId() {
        return tumId;
    }

    /**
     * Set TUM ID
     * @param tumId
     */
    public void setTumId(String tumId) {
        this.tumId = tumId;
    }

    /**
     * Get list of Project references
     * @return list of references
     */
    public List<String> getProjectIds() {
        return projectIds;
    }

    /**
     * Set list of Project references
     * @param projectIds
     */
    public void setProjectIds(List<String> projectIds) {
        this.projectIds = projectIds;
    }

    /**
     * Get if User is an admin
     * @return true if User is an admin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Set User as admin
     * @param admin
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
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
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tumId='" + tumId + '\'' +
                ", projectIds=" + projectIds +
                ", isAdmin=" + isAdmin +
                ", createdAt=" + createdAt +
                '}';
    }
}
