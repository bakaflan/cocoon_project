package com.ruiling.cocoon.business.order.representation;

import com.ruiling.cocoon.domain.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRepresentation {
    private String id;
    private OrderStatus status;

    public CreateOrderRepresentation(String id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }
}
