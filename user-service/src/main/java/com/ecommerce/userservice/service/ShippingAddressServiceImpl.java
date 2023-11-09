package com.ecommerce.userservice.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.userservice.dto.ShippingAddressDto;
import com.ecommerce.userservice.entity.ShippingAddress;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.ShippingAddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {
    private final ShippingAddressRepository shippingAddressRepository;

    @Override
    public ShippingAddress getShippingAddressById(UUID shippingAddressId) {
        return shippingAddressRepository.findById(shippingAddressId).orElseThrow(null);
    }

    @Override
    public ShippingAddress createNewShippingAddress(User user, ShippingAddressDto shippingAddressDto) {
        ShippingAddress newShippingAddress = new ShippingAddress();

        newShippingAddress.setUser(user);
        newShippingAddress.setFirstname(shippingAddressDto.getFirstName());
        newShippingAddress.setLastname(shippingAddressDto.getLastName());
        newShippingAddress.setCountry(shippingAddressDto.getCountry());
        newShippingAddress.setCity(shippingAddressDto.getCity());
        newShippingAddress.setPostalCode(shippingAddressDto.getPostalCode());
        newShippingAddress.setAddress(shippingAddressDto.getAddress());
        newShippingAddress.setContactPhone(shippingAddressDto.getContactPhone());

        newShippingAddress.setDateCreated(new Date());
        newShippingAddress.setDateLastModified(new Date());

        return shippingAddressRepository.save(newShippingAddress);
    }

    @Override
    public List<ShippingAddress> getShippingAddressListByUser(User user) {
        return shippingAddressRepository.findShippingAddressesByUser(user);
    }

}
