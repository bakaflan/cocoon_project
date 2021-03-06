package com.ruiling.cocoon.business.commodity;

import com.ruiling.cocoon.business.commodity.representation.CommodityDetailRepresentation;
import com.ruiling.cocoon.domain.commodity.Commodity;
import com.ruiling.cocoon.domain.commodity.CommodityRepository;
import com.ruiling.cocoon.infrastructure.exception.AppException;
import com.ruiling.cocoon.infrastructure.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommodityCommandService {
    private final CommodityRepository commodityRepository;

    @Transactional(readOnly = true)
    public CommodityDetailRepresentation getCommodityBy(String sku) {
        Optional<Commodity> optionalProduct = commodityRepository.bySku(sku);
        return optionalProduct
                .map(CommodityDetailRepresentation::new)
                .orElseThrow(() -> new AppException(ExceptionCode.RESOURCE_NOT_FOUND,
                        "Sku: [" + sku + "] product is not exists"));
    }
}
