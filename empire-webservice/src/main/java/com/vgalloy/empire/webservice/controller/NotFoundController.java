package com.vgalloy.empire.webservice.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vgalloy.empire.webservice.exception.NotFoundUrlException;

/**
 * Created by Vincent Galloy on 08/05/18.
 *
 * @author Vincent Galloy
 */
@RestController
public class NotFoundController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    /**
     * Throw an {@link NotFoundUrlException} in the java context.
     * Otherwise the page 404 will be automatically display
     *
     * @param request the request
     */
    @RequestMapping(ERROR_PATH)
    public void handleErrors(final HttpServletRequest request) {
        throw new NotFoundUrlException(request.getRequestURL().toString());
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
