package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Review;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


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

    public static ReviewDto toDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setOrderId(review.getProductOrder().getId());
        reviewDto.setUserId(review.getUser().getId());
        reviewDto.setProductId(review.getProduct().getId());
        reviewDto.setContents(review.getContents());
        reviewDto.setImage(review.getImage());
        reviewDto.setDeliveryScore(review.getDeliveryScore());
        reviewDto.setProductScore(review.getScore());

        return reviewDto;

    }
}
