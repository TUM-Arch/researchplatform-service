package de.tum.ar.researchplatform.service.image;

import de.tum.ar.researchplatform.model.Image;
import de.tum.ar.researchplatform.repository.ImageRepository;
import de.tum.ar.researchplatform.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by karthik on 3/29/2020
 */
@Service
@Slf4j
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Image findById(String id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public Image saveOrUpdate(Image image) {
        imageRepository.save(image);
        log.info("Updated Image: " + image);
        return image;
    }

    @Override
    public void delete(Image image) {
        imageRepository.delete(image);
        log.info("Deleted Image: " + image);
    }

    @Override
    public void deleteById(String id) {
        imageRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        imageRepository.deleteAll();
        log.info("Deleted All Images");
    }

    @Override
    public boolean existsById(String id) {
        if(imageRepository.existsById(id)) {
            return true;
        }
        return false;
    }

    @Override
    public Image addImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setImage( new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        image = this.saveOrUpdate(image);
        return image;
    }

    @Override
    public Image updateImage(MultipartFile file, String id) throws IOException {
        Image image = this.findById(id);
        image.setImage( new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        image = this.saveOrUpdate(image);
        return image;
    }
}
