package com.vgalloy.empire.webservice.dto;

import com.vgalloy.empire.webservice.dto.Dto;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public class PlayerInstructionDto implements Dto {

	private static final long serialVersionUID = -5119911425089528228L;

	private Long tax;

	public Long getTax() {
		return tax;
	}

	public void setTax(Long tax) {
		this.tax = tax;
	}
}
