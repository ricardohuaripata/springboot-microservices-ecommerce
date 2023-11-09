package com.ecommerce.userservice.service;

import java.util.List;
import java.util.UUID;

import com.ecommerce.userservice.dto.ShippingAddressDto;
import com.ecommerce.userservice.entity.ShippingAddress;
import com.ecommerce.userservice.entity.User;

public interface ShippingAddressService {
    ShippingAddress getShippingAddressById(UUID shippingAddressId);

    ShippingAddress createNewShippingAddress(User user, ShippingAddressDto shippingAddressDto);

    List<ShippingAddress> getShippingAddressListByUser(User user);

}
