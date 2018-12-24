package com.vgalloy.empire.webservice.mapper.impl;

import com.vgalloy.empire.service.model.Round;
import com.vgalloy.empire.webservice.dto.RoundDto;
import com.vgalloy.empire.webservice.mapper.RoundMapper;
import org.springframework.stereotype.Component;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class RoundMapperImpl implements RoundMapper {

  @Override
  public RoundDto map(final Round round) {
    final var result = new RoundDto();
    result.setMonth(round.getCurrentDate().getMonth().name());
    result.setYear(round.getCurrentDate().getYear());
    return result;
  }
}
