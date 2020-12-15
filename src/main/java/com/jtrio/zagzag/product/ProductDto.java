package com.jtrio.zagzag.product;

import com.jtrio.zagzag.model.Category;
import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String image;
    private Integer price;
    private String description;
    private Category category;

}
