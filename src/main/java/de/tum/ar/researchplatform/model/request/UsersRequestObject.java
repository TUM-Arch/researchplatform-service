package de.tum.ar.researchplatform.model.request;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UsersRequestObject {
	@NotNull
	private String name;
    @NotNull
    private String tumId;
    @NotNull
    private boolean admin;
}
