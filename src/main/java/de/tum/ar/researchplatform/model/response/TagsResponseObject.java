package de.tum.ar.researchplatform.model.response;

/**
 * Created by raymond on 5/31/2020
 */

import de.tum.ar.researchplatform.model.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Response object for list of all Tags
 */
@Getter
@NoArgsConstructor
public class TagsResponseObject {
    @Setter
    private int numberOfTags;
    private List<Tag> tagsList;

    /**
     * Set Tags list
     * @param tagsList
     */
    public void setTagsList(List<Tag> tagsList) {
        this.tagsList = tagsList;
        this.numberOfTags = tagsList.size();
    }
}
