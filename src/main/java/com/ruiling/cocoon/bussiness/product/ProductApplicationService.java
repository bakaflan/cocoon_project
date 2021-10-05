package com.ruiling.cocoon.bussiness.product;

import com.ruiling.cocoon.access.commodity.representation.CommodityDetailRepresentation;
import com.ruiling.cocoon.domain.commodity.Commodity;
import com.ruiling.cocoon.domain.commodity.CommodityRepository;
import com.ruiling.cocoon.infrastructure.exception.AppException;
import com.ruiling.cocoon.infrastructure.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {
    private final CommodityRepository commodityRepository;

    public CommodityDetailRepresentation getCommodityBy(String sku) {
        Optional<Commodity> optionalProduct = commodityRepository.bySku(sku);
        return optionalProduct
                .map(CommodityDetailRepresentation::new)
                .orElseThrow(() -> new AppException(ExceptionCode.REQUEST_ARGUMENT_ERROR,
                        "Sku: [" + sku + "] product is not exists"));
    }
}
