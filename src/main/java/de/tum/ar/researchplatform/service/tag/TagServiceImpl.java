package de.tum.ar.researchplatform.service.tag;

import de.tum.ar.researchplatform.exception.CustomNotFoundException;
import de.tum.ar.researchplatform.model.Tag;
import de.tum.ar.researchplatform.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static de.tum.ar.researchplatform.util.Constants.ProjectStatus.*;
import static de.tum.ar.researchplatform.util.Constants.TAG_NOT_FOUND_MSG;

/**
 * Created by raymond on 5/31/2020
 */

@Slf4j
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> listAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findById(String id) throws CustomNotFoundException {
        Tag tag = tagRepository.findById(id).orElse(null);
        if(tag == null) {
            throw new CustomNotFoundException(TAG_NOT_FOUND_MSG);
        }
        return tag;
    }

    @Override
    public Tag findByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public Tag saveOrUpdate(Tag tag) {
        tagRepository.save(tag);
        log.info("Updated Tag: " + tag.getName());
        return tag;
    }

    @Override
    public List<Tag> saveOrUpdateTags(List<String> tags) {
        List<Tag> savedAndUpdatedTags = new ArrayList<>();
        for (String tag : tags) {
            Tag existingTag = tagRepository.findByName(tag);
            if(existingTag == null) {
                Tag newTag = new Tag(tag);
                savedAndUpdatedTags.add(saveOrUpdate(newTag));
            } else {
                savedAndUpdatedTags.add(existingTag);
            }
        }
        return savedAndUpdatedTags;
    }

    @Override
    public void delete(Tag tag) {
        tagRepository.delete(tag);
        log.info("Deleted Tag: " + tag);
    }

    @Override
    public void deleteById(String id) {
        tagRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tagRepository.deleteAll();
        log.info("Deleted All Tags");
    }
}
