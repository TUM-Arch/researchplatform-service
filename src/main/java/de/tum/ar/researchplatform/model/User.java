package de.tum.ar.researchplatform.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
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
@JsonIgnoreProperties({"id"})
@Document(collection = "user")
public class User {

    @Id
    private String id; // Used by Mongo automatically

    private String name;
    private String tumid;

    private List<String> projectsReferences;

    private boolean isAdmin;

    private Date createdAt; // Used by Mongo automatically

    /**
     * No arg constructor
     */
    public User() {
        this.name = "";
        this.tumid = "";
        this.projectsReferences = new ArrayList<>();
        this.isAdmin = false;
    }

    /**
     * Constructor
     * @param name
     * @param tumid
     * @param isAdmin
     */
    public User(String name, String tumid, boolean isAdmin) {
        this.name = name;
        this.tumid = tumid;
        this.projectsReferences = new ArrayList<>();
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
    public String getTumid() {
        return tumid;
    }

    /**
     * Set TUM ID
     * @param tumid
     */
    public void setTumid(String tumid) {
        this.tumid = tumid;
    }

    /**
     * Get list of Project references
     * @return list of references
     */
    public List<String> getProjectsReferences() {
        return projectsReferences;
    }

    /**
     * Set list of Project references
     * @param projectsReferences
     */
    public void setProjectsReferences(List<String> projectsReferences) {
        this.projectsReferences = projectsReferences;
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tumid='" + tumid + '\'' +
                ", projectsReferences=" + projectsReferences +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
