package de.tum.ar.researchplatform.model.subfields;

import java.util.List;

/**
 * Created by karthik on 1/5/2020
 */
public class SelectField implements SubField {
    private String defaultValue;

    private List<String> values;

    private String selectedValue;

    public SelectField(String defaultValue, List<String> values, String selectedValue) {
        this.defaultValue = defaultValue;
        this.values = values;
        this.selectedValue = selectedValue;
    }

    public SelectField() {
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    @Override
    public String toString() {
        return "SelectField{" +
                "defaultValue='" + defaultValue + '\'' +
                ", values=" + values +
                ", selectedValue='" + selectedValue + '\'' +
                '}';
    }
}
