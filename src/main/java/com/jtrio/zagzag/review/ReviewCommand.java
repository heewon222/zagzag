package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.Max;

@Data
public class ReviewCommand {

    @Data
    public static class CreateReview {  //유저+상품 둘다필요
        private String contents;
        private String image;
        @Max(10)
        private byte productScore;
        @Max(10)
        private byte deliveryScore;

        public Review toReview(User user, ProductOrder productOrder) {
            Review reviews = new Review();
            reviews.setContents(contents);
            reviews.setImage(image);
            reviews.setUser(user);
            reviews.setProduct(productOrder.getProduct());//주문상품..오더에 있는걸 가져와야하나?
            reviews.setDeliveryScore(deliveryScore);
            reviews.setProductScore(productScore);
            reviews.setProductOrder(productOrder);

            return reviews;
        }
    }
}
