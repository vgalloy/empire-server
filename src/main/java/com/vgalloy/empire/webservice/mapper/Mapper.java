package com.vgalloy.empire.webservice.mapper;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public interface Mapper<BUSINESS, DTO /* extends Dto */> {

	DTO map(BUSINESS business);
}
