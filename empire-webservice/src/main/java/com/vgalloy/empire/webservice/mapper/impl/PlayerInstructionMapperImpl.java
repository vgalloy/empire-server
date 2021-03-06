package com.vgalloy.empire.webservice.mapper.impl;

import com.vgalloy.empire.service.model.PlayerInstructions;
import com.vgalloy.empire.webservice.dto.PlayerInstructionDto;
import com.vgalloy.empire.webservice.mapper.PlayerInstructionMapper;
import org.springframework.stereotype.Component;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class PlayerInstructionMapperImpl implements PlayerInstructionMapper {

  @Override
  public PlayerInstructionDto map(final PlayerInstructions playerInstructions) {
    final var result = new PlayerInstructionDto();
    result.setOrders(playerInstructions.getOrders());
    return result;
  }
}
