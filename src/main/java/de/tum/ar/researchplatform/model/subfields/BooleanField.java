package de.tum.ar.researchplatform.model.subfields;

/**
 * Created by karthik on 1/5/2020
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This field represents a RADIO, CHECKBOX or a SWITCH field
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BooleanField implements SubField {
    private boolean defaultValue;

    private boolean value;

    public BooleanField(boolean defaultValue, boolean isValueTrue) {
        this.defaultValue = defaultValue;
        this.value = isValueTrue;
    }
}
