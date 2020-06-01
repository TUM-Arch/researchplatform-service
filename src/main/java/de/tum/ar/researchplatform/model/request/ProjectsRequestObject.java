package de.tum.ar.researchplatform.model.request;

import de.tum.ar.researchplatform.model.Field;
import de.tum.ar.researchplatform.model.Tag;
import lombok.*;

import javax.validation.constraints.NotNull;
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
    private String imageId;
    private String userId;
    private List<Tag> tags;
    private List<Field> fields;
}
