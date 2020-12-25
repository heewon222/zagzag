package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class ReviewCommand {

    @Data
    public static class CreateReview {  //유저+상품 둘다필요
        @NotBlank
        private String contents;
        private String image;
        private List<Review> reviews;
        @Max(10)
        @Min(1)
        private byte score;
        @Max(10)
        @Min(1)
        private byte deliveryScore;
        @NotBlank
        private Long orderId;

        public Review toReview(User user, ProductOrder productOrder) {
            Review reviews = new Review();
            reviews.setContents(contents);
            reviews.setImage(image);
            reviews.setUser(user);
            reviews.setProduct(productOrder.getProduct());//주문상품..오더에 있는걸 가져와야하나?
            reviews.setDeliveryScore(deliveryScore);
            reviews.setScore(score);
            reviews.setProductOrder(productOrder);

            return reviews;
        }
    }
}
