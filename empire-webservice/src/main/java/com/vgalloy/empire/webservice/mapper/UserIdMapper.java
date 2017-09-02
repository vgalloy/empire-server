package com.vgalloy.empire.webservice.mapper;

import com.vgalloy.empire.service.model.UserId;
import com.vgalloy.empire.webservice.dto.UserIdDto;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public interface UserIdMapper extends Mapper<UserId, UserIdDto>, UnMapper<UserId, UserIdDto> {
}
