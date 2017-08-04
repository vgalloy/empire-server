package com.vgalloy.empire.webservice.mapper.impl;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.service.model.PlayerInstruction;
import com.vgalloy.empire.webservice.dto.PlayerInstructionDto;
import com.vgalloy.empire.webservice.mapper.PlayerInstructionMapper;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class PlayerInstructionMapperImpl implements PlayerInstructionMapper {

	@Override
	public PlayerInstructionDto map(PlayerInstruction playerInstruction) {
		PlayerInstructionDto result = new PlayerInstructionDto();
		result.setOrders(playerInstruction.getOrders());
		return result;
	}
}
