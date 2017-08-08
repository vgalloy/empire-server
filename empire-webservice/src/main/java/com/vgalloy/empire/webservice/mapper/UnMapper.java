package com.vgalloy.empire.webservice.mapper;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public interface UnMapper<BUSINESS, DTO> {

    /**
     * Extract the business object from a Dto one.
     *
     * @param dto the dto object
     * @return the business
     */
    BUSINESS unmap(DTO dto);
}
