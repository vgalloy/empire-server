package com.vgalloy.empire.webservice.mapper;

import com.vgalloy.empire.webservice.dto.Dto;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public interface Mapper<BUSINESS, DTO extends Dto> {

    /**
     * Map the business object into a Dto one.
     *
     * @param business the business object
     * @return the dto object
     */
    DTO map(BUSINESS business);
}
