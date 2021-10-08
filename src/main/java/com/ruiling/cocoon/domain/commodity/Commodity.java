package com.ruiling.cocoon.domain.commodity;

import com.ruiling.cocoon.infrastructure.Image;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Commodity {
    private String sku;
    private String name;
    private String description;
    private Image image;
    private BigDecimal price;

    public Commodity(String sku, String name, String description, Image image, BigDecimal price) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }
}
