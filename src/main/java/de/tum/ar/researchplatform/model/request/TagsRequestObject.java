package de.tum.ar.researchplatform.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by raymond on 5/31/2020
 **/

/**
 * Class representing a Tags Request object
 */
@Getter
@Setter
@NotNull
@NoArgsConstructor
@AllArgsConstructor
public class TagsRequestObject {
    private List<String> tags;
}