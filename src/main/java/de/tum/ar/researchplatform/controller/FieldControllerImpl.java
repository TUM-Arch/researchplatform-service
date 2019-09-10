package de.tum.ar.researchplatform.controller;

import de.tum.ar.researchplatform.service.FieldServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by karthik on 9/10/2019
 */
@Component
public class FieldControllerImpl implements FieldController {

    @Autowired
    FieldServiceImpl fieldService;
}
