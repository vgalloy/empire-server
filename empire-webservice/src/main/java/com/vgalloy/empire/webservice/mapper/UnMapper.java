package com.vgalloy.empire.webservice.mapper;

import com.vgalloy.empire.webservice.dto.Dto;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public interface UnMapper<R, T extends Dto> {

  /**
   * Extract the business object from a Dto one.
   *
   * @param dto the dto object
   * @return the business
   */
  R unmap(T dto);
}
