package com.vgalloy.empire.webservice.resource;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.core.DummyInvocationUtils;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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
    private final RequestMethod[] methods;

    /**
     * Constructor.
     *
     * @param link    the link
     * @param methods the methods for request
     */
    private LinkWithMethod(final Link link, final RequestMethod... methods) {
        super(link.getHref(), link.getRel());
        this.methods = Arrays.copyOf(methods, methods.length);
    }

    public RequestMethod[] getMethods() {
        return Arrays.copyOf(methods, methods.length);
    }

    @Override
    public LinkWithMethod withSelfRel() {
        return new LinkWithMethod(super.withSelfRel(), methods);
    }

    @Override
    public LinkWithMethod withHref(final String href) {
        return new LinkWithMethod(super.withHref(href), methods);
    }

    /**
     * Build a {@link LinkWithMethod} for the given proxy.
     *
     * @param invocationValue the proxy of the result value
     * @return a new link
     */
    public static LinkWithMethod linkTo(final Object invocationValue) {
        final var method = extractMethod(invocationValue);
        final var methodName = method.getName();
        final var link = ControllerLinkBuilder.linkTo(invocationValue).withRel(methodName);
        final var type = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        if (Objects.isNull(type) || type.method().length == 0) {
            return new LinkWithMethod(link, RequestMethod.values());
        }
        return new LinkWithMethod(link, type.method());
    }

    /**
     * Extract the current method.
     *
     * @param invocationValue the result of the method. Proxied, this object help for method extraction.
     * @return the method
     */
    private static Method extractMethod(final Object invocationValue) {
        Assert.isInstanceOf(DummyInvocationUtils.LastInvocationAware.class, invocationValue);
        final var invocations = (DummyInvocationUtils.LastInvocationAware) invocationValue;
        final var invocation = invocations.getLastInvocation();
        return invocation.getMethod();
    }
}
