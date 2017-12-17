package com.vgalloy.empire.webservice.dto;

/**
 * Created by Vincent Galloy on 20/08/17.
 *
 * @author Vincent Galloy
 */
public final class UserIdDto implements Dto {

    private static final long serialVersionUID = 190932754140605307L;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}