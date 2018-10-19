package com.vgalloy.empire.webservice.mapper.impl;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.webservice.dto.EmpireDto;
import com.vgalloy.empire.webservice.mapper.EmpireMapper;
import com.vgalloy.empire.webservice.mapper.PlayerInstructionMapper;
import com.vgalloy.empire.webservice.mapper.RoundMapper;
import com.vgalloy.empire.webservice.mapper.StockMapper;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class EmpireMapperImpl implements EmpireMapper {

    private final RoundMapper roundMapper;
    private final StockMapper stockMapper;
    private final PlayerInstructionMapper playerInstructionMapper;

    /**
     * Constructor.
     *
     * @param roundMapper             the round Mapper
     * @param stockMapper             the stock mapper
     * @param playerInstructionMapper the playerInstructionMapper
     */
    EmpireMapperImpl(final RoundMapper roundMapper, final StockMapper stockMapper, final PlayerInstructionMapper playerInstructionMapper) {
        this.roundMapper = Objects.requireNonNull(roundMapper);
        this.stockMapper = Objects.requireNonNull(stockMapper);
        this.playerInstructionMapper = Objects.requireNonNull(playerInstructionMapper);
    }

    @Override
    public EmpireDto map(final Empire empire) {
        final EmpireDto result = new EmpireDto();
        result.setPlayerInstruction(playerInstructionMapper.map(empire.getPlayerInstructions()));
        result.setRound(roundMapper.map(empire.getRound()));
        result.setGold(empire.getGold());
        result.setPopulation(empire.getPopulation());
        result.setTax(empire.getTax());
        result.setStock(stockMapper.map(empire.getStock()));
        return result;
    }
}
