package com.vgalloy.empire.webservice.controller;

import com.vgalloy.empire.webservice.exception.NotFoundUrlException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vincent Galloy on 08/05/18.
 *
 * @author Vincent Galloy
 */
@RestController
public class NotFoundController implements ErrorController {

  private static final String ERROR_PATH = "/error";

  /**
   * Throw an {@link NotFoundUrlException} in the java context. Otherwise the page 404 will be
   * automatically display
   *
   * @param request the request
   */
  @GetMapping(ERROR_PATH)
  public void handleErrors(final HttpServletRequest request) {
    throw new NotFoundUrlException(request.getRequestURL().toString());
  }

  @Override
  public String getErrorPath() {
    return ERROR_PATH;
  }
}
