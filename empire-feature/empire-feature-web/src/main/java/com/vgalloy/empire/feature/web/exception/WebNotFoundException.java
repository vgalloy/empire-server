package com.vgalloy.empire.feature.web.exception;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
public class WebNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2614371019687369882L;
    private final String id;

    /**
     * Constructor.
     *
     * @param id the missing id
     */
    public WebNotFoundException(final String id) {
        super("Enable to find resources");
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
