package com.jtrio.zagzag.order;

import lombok.Data;

@Data
public class OrderDto {

    private Long userId;
    private Long productId;
    private String userName;
    private Integer orderPrice;
    private String productName;
    private String address;


}
