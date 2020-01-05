package de.tum.ar.researchplatform.model.subfields;

/**
 * Created by karthik on 1/5/2020
 */
public class TextField implements SubField {
    private String defaultValue;

    private String value;

    public TextField(String defaultValue, String value) {
        this.defaultValue = defaultValue;
        this.value = value;
    }

    public TextField() {
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TextField{" +
                "defaultValue='" + defaultValue + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
