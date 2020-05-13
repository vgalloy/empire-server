package com.vgalloy.empire.webservice.controller;

import com.vgalloy.empire.feature.api.FeatureMethod;
import com.vgalloy.empire.webservice.dto.EmpireDto;
import com.vgalloy.empire.webservice.dto.UserDto;
import com.vgalloy.empire.webservice.resource.LinkWithMethod;
import com.vgalloy.empire.webservice.resource.ResourceData;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ControllerLinker {

  /**
   * Add all the link to the current resource.
   *
   * @param resourceData the resource
   */
  @FeatureMethod("feature.hypermedia.link")
  public void addUserLink(final ResourceData<UserDto> resourceData) {
    resourceData.add(
        LinkWithMethod.self(
            WebMvcLinkBuilder.methodOn(UserController.class).getById(resourceData.getUuid())));
    resourceData.add(
        LinkWithMethod.linkTo(
            WebMvcLinkBuilder.methodOn(UserController.class)
                .update(resourceData.getUuid(), resourceData.getResource())));
    resourceData.add(
        LinkWithMethod.linkTo(
            WebMvcLinkBuilder.methodOn(UserController.class).delete(resourceData.getUuid())));
    resourceData.add(
        LinkWithMethod.linkTo(
            WebMvcLinkBuilder.methodOn(UserController.class)
                .getEmpiresByUser(resourceData.getUuid())));
  }

  /**
   * Add all the link to the current resource.
   *
   * @param resourceData the resource
   */
  @FeatureMethod("feature.hypermedia.link")
  public void addEmpireLink(final ResourceData<EmpireDto> resourceData) {
    resourceData.add(
        LinkWithMethod.self(
            WebMvcLinkBuilder.methodOn(EmpireController.class).getById(resourceData.getUuid())));
    resourceData.add(
        LinkWithMethod.linkTo(WebMvcLinkBuilder.methodOn(EmpireController.class).getOrdersTypes()));
    resourceData.add(
        LinkWithMethod.linkTo(
            WebMvcLinkBuilder.methodOn(EmpireController.class).nextRound(resourceData.getUuid())));
    resourceData.add(
        LinkWithMethod.linkTo(
            WebMvcLinkBuilder.methodOn(EmpireController.class)
                .updateOrder(resourceData.getUuid(), null)));
  }
}
