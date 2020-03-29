package de.tum.ar.researchplatform.model.request;

import de.tum.ar.researchplatform.model.Field;
import de.tum.ar.researchplatform.util.Constants;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by raymond on 2/02/2020
 **/

/**
 * Class representing a Project Request object
 **/
@Getter
@Setter
@NotNull
@NoArgsConstructor
@AllArgsConstructor
public class ProjectsRequestObject {
    private String name;
    private String chairName;
    private String description;
    private String userId;
    private List<String> tags;
    private List<Field> fields;
    private Constants.ProjectStatus status;
}
