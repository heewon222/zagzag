package com.jtrio.zagzag.product;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ProductCommand {
    @Data
    public static class CreateProduct {
        @NotBlank
        private String name;
        @NotBlank
        private int price;
        @NotBlank
        private String image;
        private Category category;
        private Long categoryID;


        public Product toProduct(Category category) {
            Product product = new Product();

            product.setName(name);
            product.setPrice(price);
            product.setImage(image);
            product.setCategory(category);

            return product;
        }
    }

 /*   @Data
    public static class InquiryProduct{ //검색후정보읽기
        @NotBlank
        private String name;
        private int price;
        private String image;
        private Category category;

        public Product toProduct(Product product){ //정보만 조회.....정보를 불러와야함...

            product.setName(name);
            product.setPrice(price);
            product.setImage(image);
            product.setCategory(category);

            return product;
        }
    }

    @Data
    public static class UpdateProduct {
        @NotBlank
        private String name;
        private int price;
        private String image;
        private Category category;

        public Product toProduct(Product product) {

            product.setName(name);
            product.setPrice(price);
            product.setImage(image);
            product.setCategory(category);

            return product;
        }
    }*/
}

