package de.tum.ar.researchplatform.model.request;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
	private String name;

    private String tumId;

    private List<String> projectIds;

    private boolean admin;

}
