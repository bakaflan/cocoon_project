package com.ruiling.cocoon.access.order;

import com.github.database.rider.core.api.dataset.DataSet;
import com.ruiling.cocoon.BaseApiTest;
import com.ruiling.cocoon.domain.order.OrderStatus;
import com.ruiling.cocoon.business.order.command.CreateOrUpdateOrderCommand;
import com.ruiling.cocoon.infrastructure.Address;
import com.ruiling.cocoon.infrastructure.CocoonObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

class OrderControllerTest extends BaseApiTest {
    @Autowired
    CocoonObjectMapper objectMapper;

    @Test
    @DataSet("access/commodity/commodity.yml")
    void should_successfully_create_order_given_single_commodity() {
        CreateOrUpdateOrderCommand command = new CreateOrUpdateOrderCommand();
        command.setSku("1");
        command.setNumber(1);
        command.setPrice(BigDecimal.valueOf(100));
        command.setTotalPrice(BigDecimal.valueOf(100));
        command.setAddress(new Address("Tianfu 3rd road", "thoughtworks", "18081000000"));

        given()
                .body(objectMapper.writeValueAsString(command))
                .post("/api/order")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("status", is(OrderStatus.CREATED.toString()));
    }
}
