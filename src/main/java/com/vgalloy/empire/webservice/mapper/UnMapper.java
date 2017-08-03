package com.vgalloy.empire.webservice.mapper;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public interface UnMapper<BUSINESS, DTO> {

	BUSINESS unmap(DTO dto);
}
