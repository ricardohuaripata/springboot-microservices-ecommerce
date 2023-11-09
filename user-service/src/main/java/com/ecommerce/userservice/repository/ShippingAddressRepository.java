package com.ecommerce.userservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.userservice.entity.ShippingAddress;
import com.ecommerce.userservice.entity.User;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, UUID> {
    List<ShippingAddress> findShippingAddressesByUser(User user);

}
