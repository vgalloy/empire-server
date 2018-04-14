package com.vgalloy.empire.webservice.dto;

import java.util.Map;

import com.vgalloy.empire.service.model.order.OrderType;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public final class PlayerInstructionDto implements Dto {

    private static final long serialVersionUID = -5119911425089528228L;

    private Map<OrderType, Long> orders;

    public Map<OrderType, Long> getOrders() {
        return orders;
    }

    public void setOrders(final Map<OrderType, Long> orders) {
        this.orders = orders;
    }
}
