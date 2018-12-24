package com.vgalloy.empire.webservice.resource;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.core.DummyInvocationUtils;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Vincent Galloy on 18/10/18.
 *
 * @author Vincent Galloy
 */
public final class LinkWithMethod extends Link {

  private static final long serialVersionUID = -8181120740085864378L;
  private final RequestMethod method;

  /**
   * Constructor.
   *
   * @param link the link
   * @param method the method supported
   */
  private LinkWithMethod(final Link link, final RequestMethod method) {
    super(link.getHref(), link.getRel());
    this.method = Objects.requireNonNull(method);
  }

  public RequestMethod getMethod() {
    return method;
  }

  @Override
  public LinkWithMethod withSelfRel() {
    return new LinkWithMethod(super.withSelfRel(), method);
  }

  @Override
  public LinkWithMethod withHref(final String href) {
    return new LinkWithMethod(super.withHref(href), method);
  }

  /**
   * Build all available {@link LinkWithMethod} for the given proxy.
   *
   * @param invocationValue the proxy of the result value
   * @return new links
   */
  public static LinkWithMethod[] linkTo(final Object invocationValue) {
    final var method = extractMethod(invocationValue);
    final var methodName = method.getName();
    final var link = ControllerLinkBuilder.linkTo(invocationValue).withRel(methodName);
    final var requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
    final var requestMethods = getRequestMethod(requestMapping);
    return Arrays.stream(requestMethods)
        .map(requestMethod -> new LinkWithMethod(link, requestMethod))
        .toArray(LinkWithMethod[]::new);
  }

  /**
   * Build one {@link LinkWithMethod} for the given proxy.
   *
   * @param invocationValue the proxy of the result value
   * @return a new self link
   */
  public static LinkWithMethod self(final Object invocationValue) {
    final var links = linkTo(invocationValue);
    Assert.state(links.length == 1, "Only one link can be self");
    return links[0].withSelfRel();
  }

  /**
   * Extract the request mapping. If no mapping is specified, all verb will be available.
   *
   * @param requestMapping the request mapping value
   * @return the list of available request mapping
   */
  private static RequestMethod[] getRequestMethod(@Nullable final RequestMapping requestMapping) {
    if (Objects.isNull(requestMapping) || requestMapping.method().length == 0) {
      return RequestMethod.values();
    }
    return requestMapping.method();
  }

  /**
   * Extract the current method.
   *
   * @param invocationValue the result of the method. Proxied, this object help for method
   *     extraction.
   * @return the method
   */
  private static Method extractMethod(final Object invocationValue) {
    Assert.isInstanceOf(DummyInvocationUtils.LastInvocationAware.class, invocationValue);
    final var invocations = (DummyInvocationUtils.LastInvocationAware) invocationValue;
    final var invocation = invocations.getLastInvocation();
    return invocation.getMethod();
  }
}
