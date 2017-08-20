package com.vgalloy.empire.webservice.mapper.impl;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.webservice.dto.EmpireDto;
import com.vgalloy.empire.webservice.mapper.EmpireIdMapper;
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
    private final EmpireIdMapper empireIdMapper;
    private final PlayerInstructionMapper playerInstructionMapper;

    /**
     * Constructor.
     *
     * @param roundMapper             the round Mapper
     * @param stockMapper             the stock mapper
     * @param empireIdMapper          empire id mapper
     * @param playerInstructionMapper the playerInstructionMapper
     */
    EmpireMapperImpl(RoundMapper roundMapper, StockMapper stockMapper, EmpireIdMapper empireIdMapper, PlayerInstructionMapper playerInstructionMapper) {
        this.roundMapper = Objects.requireNonNull(roundMapper);
        this.stockMapper = Objects.requireNonNull(stockMapper);
        this.empireIdMapper = Objects.requireNonNull(empireIdMapper);
        this.playerInstructionMapper = Objects.requireNonNull(playerInstructionMapper);
    }

    @Override
    public EmpireDto map(Empire empire) {
        EmpireDto result = new EmpireDto();
        result.setEmpireId(empireIdMapper.map(empire.getEmpireId()));
        result.setPlayerInstruction(playerInstructionMapper.map(empire.getPlayerInstructions()));
        result.setRound(roundMapper.map(empire.getRound()));
        result.setGold(empire.getGold());
        result.setPopulation(empire.getPopulation());
        result.setTax(empire.getTax());
        result.setStock(stockMapper.map(empire.getStock()));
        return result;
    }
}
