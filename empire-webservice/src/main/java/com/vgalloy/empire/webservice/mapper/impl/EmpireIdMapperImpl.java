package com.vgalloy.empire.webservice.mapper.impl;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.service.model.EmpireId;
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
    public String map(EmpireId empireId) {
        return empireId.getId();
    }

    @Override
    public EmpireId unmap(String empireIdAsString) {
        UserInputException.requireNonNullNonEmptyNonBlank(empireIdAsString, "Invalid empire id");

        return EmpireId.of(empireIdAsString);
    }
}
