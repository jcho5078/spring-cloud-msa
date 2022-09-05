package com.example.catalogservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CatalogDto implements Serializable {

    private String productName;
    private Integer price;

    private String orderId;
    private String userId;
}
