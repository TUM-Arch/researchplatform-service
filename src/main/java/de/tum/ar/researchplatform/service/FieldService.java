package de.tum.ar.researchplatform.service;

import de.tum.ar.researchplatform.model.Field;

/**
 * Created by karthik on 9/10/2019
 */
public interface FieldService {

    /**
     * List all Field objects
     * @return All Field objects
     */
    Iterable listAll();

    /**
     * Find Field object by id
     * @param id
     * @return Field object found
     */
    Field findById(String id);

    /**
     * Save or Update Field object
     * @param field
     * @return Field object saved or updated
     */
    Field saveOrUpdate(Field field);

    /**
     * Delete Field object
     * @param field
     */
    void delete(Field field);

    /**
     * Delete Field object by id
     * @param id
     */
    void deleteById(String id);

    /**
     * Check if Field object exists by id
     * @param id
     * @return true if it exists
     */
    boolean existsById(String id);

    /**
     * Delete all Field objects
     */
    void deleteAll();

    /**
     * Find Field object by English name
     * @param nameEn
     * @return Field object
     */
    public Field findByNameEn(String nameEn);

    /**
     * Find Field object by German name
     * @param nameDe
     * @return Field object
     */
    public Field findByNameDe(String nameDe);
}
