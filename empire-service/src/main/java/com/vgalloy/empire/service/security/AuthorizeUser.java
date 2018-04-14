package com.vgalloy.empire.service.security;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
@PreAuthorize("hasRole('USER')")
public @interface AuthorizeUser {
}
