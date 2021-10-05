package com.ruiling.cocoon.domain.order;

import com.ruiling.cocoon.domain.commodity.Commodity;
import com.ruiling.cocoon.domain.order.command.CreateOrUpdateOrderCommand;
import com.ruiling.cocoon.infrastructure.Address;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Order {
    private String id;
    private List<OrderItem> items = new ArrayList<>();
    private Address address;
    private OrderStatus status;
    private Instant updatedAt;
    private Instant createdAt;

    @Getter
    @Setter
    public static class OrderItem {
        private Integer itemId;
        private Integer quantity;
        private BigDecimal totalPrice;
        private Commodity commodity;

        public OrderItem(Integer itemId, Integer quantity, Commodity commodity) {
            this.itemId = itemId;
            this.quantity = quantity;
            this.totalPrice = commodity.getPrice().multiply(BigDecimal.valueOf(quantity));
            this.commodity = commodity;
        }
    }

    public void addCommodity(Integer quantity, Commodity commodity) {
        items.add(new OrderItem(nextLubricantPointId(), quantity, commodity));
    }

    private int nextLubricantPointId() {
        return this.items.stream()
                .mapToInt(OrderItem::getItemId)
                .max()
                .orElse(0) + 1;
    }

    public static Order of(CreateOrUpdateOrderCommand command, Commodity commodity) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.addCommodity(command.getNumber(), commodity);
        order.setAddress(command.getAddress());
        order.setStatus(OrderStatus.CREATED);
        Instant timestamp = Instant.now();
        order.setCreatedAt(timestamp);
        order.setUpdatedAt(timestamp);
        return order;
    }
}
