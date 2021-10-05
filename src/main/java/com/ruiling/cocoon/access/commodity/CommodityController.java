package com.ruiling.cocoon.access.commodity;

import com.ruiling.cocoon.access.commodity.representation.CommodityDetailRepresentation;
import com.ruiling.cocoon.bussiness.product.ProductApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommodityController {
    private final ProductApplicationService productApplicationService;

    @GetMapping("/api/commodity/{id}")
    public CommodityDetailRepresentation getCommodityDetail(@PathVariable(value = "id") String sku) {
        return productApplicationService.getCommodityBy(sku);
    }
}
