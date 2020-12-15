package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.User;
import lombok.Data;


@Data
public class ReviewDto {
    private String image;
    private String contents;
    private byte productScore;
    private byte deliveryScore;
    private User user;
    private Product product;
}
