package com.jtrio.zagzag.review;

import lombok.Data;


@Data
public class ReviewDto {
    private Long userId;
    private Long productId;
    private Long orderId;
    private String image;
    private String contents;
    private byte productScore;
    private byte deliveryScore;
    private String userName;
    private String productName;

}
