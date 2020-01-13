package de.tum.ar.researchplatform.model.subfields;

/**
 * Created by karthik on 1/5/2020
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This field represents a TEXT or a MULTITEXT text field
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TextField implements SubField {
    private String defaultValue;

    private String value;

    public TextField(String defaultValue, String value) {
        this.defaultValue = defaultValue;
        this.value = value;
    }
}
