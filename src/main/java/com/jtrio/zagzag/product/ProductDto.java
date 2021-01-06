package com.jtrio.zagzag.product;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String image;
    private Integer price;
    private String description;
    private Long categoryId;
    private int quantity;

    public static ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        productDto.setQuantity(product.getQuantity());
        productDto.setDescription(productDto.getDescription());
        productDto.setCategoryId(product.getCategory().getId());

        return productDto;
    }

}
