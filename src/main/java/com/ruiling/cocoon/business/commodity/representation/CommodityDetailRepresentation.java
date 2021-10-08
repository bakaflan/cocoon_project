package com.ruiling.cocoon.business.commodity.representation;

import com.ruiling.cocoon.domain.commodity.Commodity;
import com.ruiling.cocoon.infrastructure.Image;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CommodityDetailRepresentation {
    private String sku;
    private String name;
    private String description;
    private Image image;
    private BigDecimal price;

    public CommodityDetailRepresentation(Commodity commodity) {
        this.sku = commodity.getSku();
        this.name = commodity.getName();
        this.description = commodity.getDescription();
        this.image = commodity.getImage();
        this.price = commodity.getPrice();
    }
}
