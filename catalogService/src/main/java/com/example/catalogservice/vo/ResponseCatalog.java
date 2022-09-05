package com.example.catalogservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseCatalog {

    private String productName;
    private String price;
    private Data createDt;

    private String orderId;
    private String userId;
}
