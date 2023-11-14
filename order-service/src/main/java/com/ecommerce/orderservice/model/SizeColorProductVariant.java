package com.ecommerce.orderservice.model;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeColorProductVariant {
    private UUID id;
    private ColorProductVariant colorProductVariant;
    private String size;
    private int stock;
    private Date dateCreated;
    private Date dateLastModified;

}
