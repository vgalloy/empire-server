package com.vgalloy.empire.webservice.controller;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import com.vgalloy.empire.feature.api.FeatureMethod;
import com.vgalloy.empire.webservice.dto.UserDto;
import com.vgalloy.empire.webservice.resource.LinkWithMethod;
import com.vgalloy.empire.webservice.resource.ResourceData;

@Component
public class ControllerLinker {

    /**
     * Add all the link to the current resource.
     *
     * @param resourceData the resource
     */
    @FeatureMethod("feature.hypermedia.link")
    public void addLink(final ResourceData<UserDto> resourceData) {
        resourceData.add(LinkWithMethod.linkTo(ControllerLinkBuilder.methodOn(UserController.class).getById(resourceData.getUuid())).withSelfRel());
        resourceData.add(LinkWithMethod.linkTo(ControllerLinkBuilder.methodOn(UserController.class).update(resourceData.getUuid(), resourceData.getResource())));
        resourceData.add(LinkWithMethod.linkTo(ControllerLinkBuilder.methodOn(UserController.class).delete(resourceData.getUuid())));
        resourceData.add(LinkWithMethod.linkTo(ControllerLinkBuilder.methodOn(UserController.class).getEmpiresByUser(resourceData.getUuid())));
    }
}
