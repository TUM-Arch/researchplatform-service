package de.tum.ar.researchplatform.model.response;

/**
 * Created by karthik on 4/18/2020
 */

import de.tum.ar.researchplatform.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response object for project whose status has been advanced
 */
@Getter
@Setter
@NoArgsConstructor
public class ProjectWorkflowAdvancedResponseObject {
    private String id;
    private Constants.ProjectStatus status;
}
