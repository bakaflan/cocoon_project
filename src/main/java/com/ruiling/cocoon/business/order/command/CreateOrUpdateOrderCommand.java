package com.ruiling.cocoon.business.order.command;

import com.ruiling.cocoon.infrastructure.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class CreateOrUpdateOrderCommand {
    @NotBlank
    private String sku;
    @NotNull
    private Integer number;
    private BigDecimal price;
    private BigDecimal totalPrice;
    @Valid
    private Address address;
}
