package de.tum.ar.researchplatform.model.request;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

/**
 * Created by raymond on 1/27/2020
 **/

/**
 * Class representing a User Request object
 */
@Getter
@Setter
@NoArgsConstructor
public class UsersRequestObject {
	@NotNull
	private String name;
    @NotNull
    private String tumId;
    @NotNull
    private List<String> projectIds;
    @NotNull
    private boolean admin;

}
