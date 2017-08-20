package com.vgalloy.empire.webservice.dto;

/**
 * Created by Vincent Galloy on 20/08/17.
 *
 * @author Vincent Galloy
 */
public final  class EmpireIdDto implements Dto {

    private static final long serialVersionUID = 7557642900297198139L;

    private String empireId;

    public String getEmpireId() {
        return empireId;
    }

    public void setEmpireId(String empireId) {
        this.empireId = empireId;
    }
}
