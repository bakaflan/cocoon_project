package com.ruiling.cocoon.infrastructure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class Address {
    @NotBlank(message = "请输入您的收货地址")
    private String address;
    @NotBlank(message = "请填写您的姓名")
    private String name;
    @NotBlank
    @Pattern(regexp = "^1[345678]\\d{9}$", message = "请输入正确的手机号码")
    private String phone;

    public Address(String address, String name, String phone) {
        this.address = address;
        this.name = name;
        this.phone = phone;
    }
}
