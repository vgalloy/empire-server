package com.vgalloy.empire.webservice.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Vincent Galloy on 20/08/17.
 *
 * @author Vincent Galloy
 */
public final class UserIdDto implements Dto {

    private static final long serialVersionUID = 190932754140605307L;

    @NotEmpty(message = "Invalid user id")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }
}
