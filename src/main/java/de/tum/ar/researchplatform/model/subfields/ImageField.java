package de.tum.ar.researchplatform.model.subfields;

/**
 * Created by karthik on 1/13/2020
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * This field represents an IMAGE field
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ImageField implements SubField {
    @Id
    private String imageId;

    public String getImageId() {
        return imageId;
    }
}
