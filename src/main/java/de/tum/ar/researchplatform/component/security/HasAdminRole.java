package de.tum.ar.researchplatform.component.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by karthik on 5/16/2020
 */
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('ADMIN')")
public @interface HasAdminRole {
}
