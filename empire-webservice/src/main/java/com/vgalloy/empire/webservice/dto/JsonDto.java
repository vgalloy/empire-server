package com.vgalloy.empire.webservice.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Create by Vincent Galloy on 02/08/2017. This class is the root interface for all information send
 * and received by the application in json format. These classes mustn't include business logic.
 *
 * @author Vincent Galloy
 */
@JsonPropertyOrder(alphabetic = true)
public interface JsonDto {}
