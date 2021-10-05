package com.ruiling.cocoon.bussiness.order;

import com.ruiling.cocoon.access.order.representation.CreateOrderRepresentation;
import com.ruiling.cocoon.domain.commodity.Commodity;
import com.ruiling.cocoon.domain.commodity.CommodityRepository;
import com.ruiling.cocoon.domain.order.Order;
import com.ruiling.cocoon.domain.order.OrderRepository;
import com.ruiling.cocoon.domain.order.command.CreateOrUpdateOrderCommand;
import com.ruiling.cocoon.infrastructure.exception.AppException;
import com.ruiling.cocoon.infrastructure.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderCommandService {
    private final OrderRepository orderRepository;
    private final CommodityRepository commodityRepository;

    @Transactional
    public CreateOrderRepresentation createOrder(CreateOrUpdateOrderCommand command) {
        Commodity commodity = commodityRepository.bySku(command.getSku())
                .orElseThrow(() -> new AppException(ExceptionCode.REQUEST_ARGUMENT_ERROR, "该商品不存在"));
        Order order = Order.of(command, commodity);
        orderRepository.save(order);
        return new CreateOrderRepresentation(order.getId(), order.getStatus());
    }
}
