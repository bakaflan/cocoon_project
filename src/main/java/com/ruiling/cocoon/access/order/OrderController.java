package com.ruiling.cocoon.access.order;

import com.ruiling.cocoon.business.order.representation.CreateOrderRepresentation;
import com.ruiling.cocoon.business.order.OrderCommandService;
import com.ruiling.cocoon.business.order.command.CreateOrUpdateOrderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderCommandService orderCommandService;

    @PostMapping("/api/order")
    @ResponseStatus(CREATED)
    public CreateOrderRepresentation createOrder(@RequestBody @Valid CreateOrUpdateOrderCommand command) {
        return orderCommandService.createOrder(command);
    }
}
