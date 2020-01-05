package de.tum.ar.researchplatform.model.subfields;

/**
 * Created by karthik on 1/5/2020
 */
public class BooleanField implements SubField {
    private boolean defaultValue;

    private boolean value;

    public BooleanField(boolean defaultValue, boolean value) {
        this.defaultValue = defaultValue;
        this.value = value;
    }

    public BooleanField() {
    }

    public boolean isDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "BooleanField{" +
                "defaultValue=" + defaultValue +
                ", value=" + value +
                '}';
    }
}
