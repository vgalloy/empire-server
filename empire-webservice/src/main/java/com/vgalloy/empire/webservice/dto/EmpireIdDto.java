package com.vgalloy.empire.webservice.dto;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Vincent Galloy on 20/08/17.
 *
 * @author Vincent Galloy
 */
public final class EmpireIdDto implements Dto {

    private static final long serialVersionUID = 7557642900297198139L;

    @NotEmpty(message = "Invalid empire id")
    private String empireId;

    public String getEmpireId() {
        return empireId;
    }

    public void setEmpireId(final String empireId) {
        this.empireId = empireId;
    }
}
