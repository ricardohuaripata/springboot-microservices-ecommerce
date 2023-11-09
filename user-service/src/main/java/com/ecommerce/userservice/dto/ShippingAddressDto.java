package com.ecommerce.userservice.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingAddressDto {
    @Size(max = 64)
    private String firstName;

    @Size(max = 64)
    private String lastName;

    @Size(max = 64)
    private String country;

    @Size(max = 64)
    private String city;

    @Size(max = 10)
    private String postalCode;

    @Size(max = 1024)
    private String address;

    @Size(max = 20)
    private String contactPhone;
}
