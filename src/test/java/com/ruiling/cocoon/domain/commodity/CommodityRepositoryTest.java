package com.ruiling.cocoon.domain.commodity;

import com.github.database.rider.core.api.dataset.DataSet;
import com.ruiling.cocoon.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommodityRepositoryTest extends BaseTest {
    @Autowired
    private CommodityRepository commodityRepository;

    @Test
    @DataSet("/domain/commodity/commodity.yml")
    void should_successfully_retrieve_commodity_by_id() {
        String commoditySku = "1";
        Optional<Commodity> optionalCommodity = commodityRepository.bySku(commoditySku);
        assertTrue(optionalCommodity.isPresent());
        Commodity commodity = optionalCommodity.get();
        assertEquals(commodity.getSku(), commoditySku);
        assertEquals(commodity.getDescription(), "描述");
        assertEquals(commodity.getName(), "测试用商品");
        assertEquals(commodity.getPrice(), BigDecimal.valueOf(100));
        assertNotNull(commodity.getImage());
        assertNotNull(commodity.getImage().getUrl());
        assertNotNull(commodity.getImage().getThumbnailUrl());
    }
}
