package de.tum.ar.researchplatform.service;

import de.tum.ar.researchplatform.model.Form;

/**
 * Created by karthik on 9/10/2019
 */
public interface FormService {
    /**
     * List all Form objects
     * @return All Form objects
     */
    Iterable listAll();

    /**
     * Find Form object by id
     * @param id
     * @return Form object found
     */
    Form findById(String id);

    /**
     * Find Form object by name
     * @param name
     * @return Form object found
     */
    Form findByName(String name);

    /**
     * Save or Update Form object
     * @param form
     * @return Form object saved or updated
     */
    Form saveOrUpdate(Form form);

    /**
     * Delete Form object
     * @param form
     */
    void delete(Form form);

    /**
     * Delete Form object by id
     * @param id
     */
    void deleteById(String id);

    /**
     * Check if Form object exists by id
     * @param id
     * @return true if it exists
     */
    boolean existsById(String id);

    /**
     * Delete all Form objects
     */
    void deleteAll();
}
