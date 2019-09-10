package de.tum.ar.researchplatform.model;

/**
 * Created by karthik on 9/10/2019
 */

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
    private String defaultValue;

    private Constants.FieldType type;

    private boolean isRequired;

    private Date createdAt;

    /**
     * No arg constructor
     */
    public Field() {
        this.createdAt = new Date();
        this.nameEn = "";
        this.nameDe = "";
        this.defaultValue = "";
        this.type = Constants.FieldType.TEXT;
        this.isRequired = false;
    }

    /**
     * Constructor
     * @param nameEn
     * @param nameDe
     * @param defaultValue
     * @param type
     * @param isRequired
     */
    public Field(String nameEn, String nameDe, String defaultValue, Constants.FieldType type, boolean isRequired) {
        this.createdAt = new Date();
        this.nameEn = nameEn;
        this.nameDe = nameDe;
        this.defaultValue = defaultValue;
        this.type = type;
        this.isRequired = isRequired;
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
     * Get default value given
     * @return default value
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Set default value
     * @param defaultValue
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
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
                ", defaultValue='" + defaultValue + '\'' +
                ", type=" + type +
                ", isRequired=" + isRequired +
                ", createdAt=" + createdAt +
                '}';
    }
}
