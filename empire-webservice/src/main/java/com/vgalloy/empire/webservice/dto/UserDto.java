package com.vgalloy.empire.webservice.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public final  class UserDto implements Dto {

    private static final long serialVersionUID = -1914355807800734302L;

    @NotEmpty(message = "Invalid user login")
    private String login;
    @NotEmpty(message = "Invalid user password")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
