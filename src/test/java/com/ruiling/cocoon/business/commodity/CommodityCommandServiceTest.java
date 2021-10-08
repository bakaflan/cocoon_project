package com.ruiling.cocoon.business.commodity;

import com.ruiling.cocoon.BaseComponentTest;
import com.ruiling.cocoon.business.commodity.representation.CommodityDetailRepresentation;
import com.ruiling.cocoon.domain.commodity.Commodity;
import com.ruiling.cocoon.domain.commodity.CommodityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommodityCommandServiceTest extends BaseComponentTest {
    private CommodityRepository commodityRepository;
    private CommodityCommandService commodityCommandService;

    @BeforeEach
    public void setUp() {
        commodityRepository = mock(CommodityRepository.class);
        commodityCommandService = new CommodityCommandService(commodityRepository);
    }

    @Test
    public void should_successfully_when_retrieve_commodity_given_sku() {
        String sku = "1";
        Commodity mockCommodity = new Commodity(sku, "name", "description", null, BigDecimal.valueOf(100));
        when(commodityRepository.bySku(anyString())).thenReturn(Optional.of(mockCommodity));
        CommodityDetailRepresentation representation = commodityCommandService.getCommodityBy(sku);

        verify(commodityRepository, times(1)).bySku(anyString());
        assertEquals(representation.getSku(), sku);
        assertEquals(representation.getDescription(), mockCommodity.getDescription());
        assertEquals(representation.getName(), mockCommodity.getName());
        assertEquals(representation.getPrice(), mockCommodity.getPrice());
    }
}
