package com.ruiling.cocoon.access.commodity;

import com.github.database.rider.core.api.dataset.DataSet;
import com.ruiling.cocoon.BaseApiTest;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;

class CommodityControllerTest extends BaseApiTest {
    @Test
    @DataSet("access/commodity/commodity.yml")
    void should_successfully_get_commodity_detail_given_product_id() {
        given()
                .get("/api/commodity/1")
                .then()
                .statusCode(200)
                .body("sku", is("1"))
                .body("name", is("测试用商品"))
                .body("image.url", CoreMatchers.notNullValue())
                .body("price", is(100));
    }
}
