package de.tum.ar.researchplatform.model.subfields;

/**
 * Created by karthik on 1/5/2020
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This field represents a DATE or a TIME field
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TimeDateField implements SubField {
    private String defaultValue;

    private String value;

    public TimeDateField(String defaultValue, String value) {
        this.defaultValue = defaultValue;
        this.value = value;
    }
}
