package com.ruiling.cocoon.business.order;

import com.ruiling.cocoon.business.order.command.CreateOrUpdateOrderCommand;
import com.ruiling.cocoon.domain.commodity.Commodity;
import com.ruiling.cocoon.domain.commodity.CommodityRepository;
import com.ruiling.cocoon.domain.order.Order;
import com.ruiling.cocoon.domain.order.OrderRepository;
import com.ruiling.cocoon.infrastructure.Address;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderCommandServiceTest {
    private OrderRepository orderRepository;
    private CommodityRepository commodityRepository;
    private OrderCommandService orderCommandService;

    @Before
    public void setUp() {
        orderRepository = mock(OrderRepository.class);
        commodityRepository = mock(CommodityRepository.class);
        orderCommandService = new OrderCommandService(orderRepository, commodityRepository);
    }

    @Test
    public void should_successfully_when_save_order_given_validated_command() {
        Commodity mockCommodity = new Commodity("1", "name", "description", null, BigDecimal.valueOf(100));
        when(commodityRepository.bySku(anyString())).thenReturn(of(mockCommodity));
        doNothing().when(orderRepository).save(any(Order.class));

        CreateOrUpdateOrderCommand mockCommand = new CreateOrUpdateOrderCommand();
        mockCommand.setSku("1");
        mockCommand.setNumber(1);
        mockCommand.setPrice(BigDecimal.valueOf(1));
        mockCommand.setTotalPrice(BigDecimal.valueOf(1));
        mockCommand.setAddress(new Address("address", "name", "18081000000"));

        orderCommandService.createOrder(mockCommand);

        verify(orderRepository, times(1)).save(any(Order.class));
        verify(commodityRepository, times(1)).bySku(eq(mockCommand.getSku()));
    }
}
