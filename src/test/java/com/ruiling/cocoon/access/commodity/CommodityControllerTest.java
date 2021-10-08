package com.ruiling.cocoon.access.commodity;

import com.github.database.rider.core.api.dataset.DataSet;
import com.ruiling.cocoon.BaseTest;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;

public class CommodityControllerTest extends BaseTest {
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

    @Test
    @DataSet("access/commodity/commodity.yml")
    void should_not_found_commodity_detail_given_not_exists_id() {
        given()
                .get("/api/commodity/2")
                .then()
                .statusCode(404);
    }
}
