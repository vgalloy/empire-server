package com.vgalloy.empire.webservice.dto;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Vincent Galloy on 20/08/17.
 *
 * @author Vincent Galloy
 */
public final class UserIdDto implements Dto {

    @NotEmpty(message = "Invalid user id")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }
}
