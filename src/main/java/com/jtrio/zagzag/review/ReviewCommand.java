package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import lombok.Data;

@Data
public class ReviewCommand {

    @Data
    public static class CreateReview{  //유저+상품 둘다필요
        private Long userId;
        private Long productId;
        private String contents;
        private String image;
        private byte productScore;
        private byte deliveryScore;

        public Review toReview(User user, Product product){
            Review reviews=new Review();
            reviews.setContents(contents);
            reviews.setImage(image);
            reviews.setUser(user);
            reviews.setProduct(product);
            reviews.setDeliveryScore(deliveryScore);
            reviews.setProductScore(productScore);


            return reviews;
        }
    }
}
