package de.tum.ar.researchplatform.model.request;

/**
 * Created by karthik on 4/18/2020
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Class representing a Field Request object
 */
@Getter
@Setter
@NotNull
@NoArgsConstructor
@AllArgsConstructor
public class FieldsRequestObject {

    private String nameEn;
    private String nameDe;

    private String valueEn;
    private String valueDe;

    private String description;

    private boolean required;
    private boolean active;
}
