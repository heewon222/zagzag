package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.ProductOrder;
import lombok.Data;

@Data
public class OrderDto {

    private Long userId;
    private Long productId;
    private String userName;
    private Integer orderPrice;
    private String productName;
    private String address;

    public static OrderDto toDto(ProductOrder productOrder) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderPrice(productOrder.getPrice());
        orderDto.setProductId(productOrder.getProduct().getId());
        orderDto.setUserId(productOrder.getUser().getId());

        return orderDto;
    }


}
