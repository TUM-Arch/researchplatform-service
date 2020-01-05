package de.tum.ar.researchplatform.model;

/**
 * Created by karthik on 9/10/2019
 */

import de.tum.ar.researchplatform.model.subfields.SubField;
import de.tum.ar.researchplatform.model.subfields.TextField;
import de.tum.ar.researchplatform.util.Constants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Class representing a FormField object
 */
@Document(collection = "field")
public class Field {

    @Id
    private String id; // Used by Mongo automatically

    private String nameEn;
    private String nameDe;

    private Constants.FieldType type;
    private SubField subField;

    private boolean isRequired;
    private boolean isActive;

    private Date createdAt;

    /**
     * No arg constructor
     * Creates empty text field
     */
    public Field() {
        this.createdAt = new Date();
        this.nameEn = "";
        this.nameDe = "";
        this.type = Constants.FieldType.TEXT;
        this.subField = new TextField();
        this.isRequired = false;
        this.isActive = false;
    }

    /**
     * Constructor
     * @param nameEn
     * @param nameDe
     * @param subField
     * @param type
     * @param isRequired
     * @param isActive
     */
    public Field(String nameEn, String nameDe, SubField subField, Constants.FieldType type, boolean isRequired, boolean isActive) {
        this.createdAt = new Date();
        this.nameEn = nameEn;
        this.nameDe = nameDe;
        this.type = type;
        this.subField = subField;
        this.isRequired = isRequired;
        this.isActive = isActive;
    }

    /**
     * Get id for Field object
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Get English string for name
     * @return name in English
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * Set English string for name
     * @param nameEn
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    /**
     * Get German string for name
     * @return name in German
     */
    public String getNameDe() {
        return nameDe;
    }

    /**
     * Set German string for name
     * @param nameDe
     */
    public void setNameDe(String nameDe) {
        this.nameDe = nameDe;
    }

    /**
     * Get field type
     * @return field type
     */
    public Constants.FieldType getType() {
        return type;
    }

    /**
     * Set field type
     * @param type
     */
    public void setType(Constants.FieldType type) {
        this.type = type;
    }

    /**
     * Get sub field
     * @return subfield
     */
    public SubField getSubField() {
        return subField;
    }

    /**
     * Set sub field
     * @param subField
     */
    public void setSubField(SubField subField) {
        this.subField = subField;
    }

    /**
     * Get if field is marked required
     * @return true if field marked required
     */
    public boolean isRequired() {
        return isRequired;
    }

    /**
     * Set field marked as required
     * @param required
     */
    public void setRequired(boolean required) {
        isRequired = required;
    }

    /**
     * Get if field is marked active
     * @return true if field marked active
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Set field marked as active
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
        return "Field{" +
                "id='" + id + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", nameDe='" + nameDe + '\'' +
                ", type=" + type +
                ", subField=" + subField +
                ", isRequired=" + isRequired +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                '}';
    }
}
