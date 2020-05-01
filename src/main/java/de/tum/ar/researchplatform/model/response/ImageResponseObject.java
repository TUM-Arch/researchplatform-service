package de.tum.ar.researchplatform.model.response;

/**
 * Created by karthik on 5/1/2020
 */

import de.tum.ar.researchplatform.model.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Base64;

/**
 * Response object for an image
 */
@Getter
@Setter
@NoArgsConstructor
public class ImageResponseObject {
    private String imageId;
    private String image;

    public ImageResponseObject(Image image) {
        this.imageId = image.getId();
        if(image.getImage() != null) {
            this.image = Base64.getEncoder().encodeToString(image.getImage().getData());
        }
    }
}
