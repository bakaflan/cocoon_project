package com.ruiling.cocoon.access.commodity;

import com.ruiling.cocoon.access.commodity.representation.CommodityDetailRepresentation;
import com.ruiling.cocoon.bussiness.commodity.ProductCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommodityController {
    private final ProductCommandService productCommandService;

    @GetMapping("/api/commodity/{id}")
    public CommodityDetailRepresentation getCommodityDetail(@PathVariable(value = "id") String sku) {
        return productCommandService.getCommodityBy(sku);
    }
}
