package com.vgalloy.empire.webservice.mapper.impl;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.service.model.EmpireId;
import com.vgalloy.empire.webservice.dto.EmpireIdDto;
import com.vgalloy.empire.webservice.exception.UserInputException;
import com.vgalloy.empire.webservice.mapper.EmpireIdMapper;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class EmpireIdMapperImpl implements EmpireIdMapper {

    @Override
    public EmpireIdDto map(EmpireId empireId) {
        EmpireIdDto result = new  EmpireIdDto();
        result.setEmpireId(empireId.getId());
        return result;
    }

    @Override
    public EmpireId unmap(EmpireIdDto empireIdDto) {
        UserInputException.requireNonNull(empireIdDto, "Empire id can't be null");
        UserInputException.requireNonNullNonEmptyNonBlank(empireIdDto.getEmpireId(), "Invalid empire id");

        return EmpireId.of(empireIdDto.getEmpireId());
    }
}
