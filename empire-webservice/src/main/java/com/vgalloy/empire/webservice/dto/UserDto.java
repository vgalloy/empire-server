package com.vgalloy.empire.webservice.dto;

import javax.validation.constraints.NotEmpty;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public class UserDto implements Dto {

    @NotEmpty(message = "Invalid user login")
    private String login;
    @NotEmpty(message = "Invalid user password")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
