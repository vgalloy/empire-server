package com.vgalloy.empire.webservice.mapper.impl;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.service.model.Round;
import com.vgalloy.empire.webservice.dto.RoundDto;
import com.vgalloy.empire.webservice.mapper.RoundMapper;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class RoundMapperImpl implements RoundMapper {

    @Override
    public RoundDto map(Round round) {
        RoundDto result = new RoundDto();
        result.setMonth(round.getCurrentDate().getMonth().name());
        result.setYear(round.getCurrentDate().getYear());
        return result;
    }
}
