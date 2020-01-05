package de.tum.ar.researchplatform.model.subfields;

/**
 * Created by karthik on 1/5/2020
 */
public class TimeDateField implements SubField {
    private String defaultValue;

    private String value;

    public TimeDateField(String defaultValue, String value) {
        this.defaultValue = defaultValue;
        this.value = value;
    }

    public TimeDateField() {
    }

    public String isDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String isValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TimeDateField{" +
                "defaultValue=" + defaultValue +
                ", value=" + value +
                '}';
    }
}
