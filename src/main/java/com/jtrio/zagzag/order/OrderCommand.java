package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderCommand {
    private Long productId;

    public ProductOrder toOrder(Product product, User user) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setPrice(product.getPrice());
        productOrder.setUser(user);
        productOrder.setProduct(product);

        return productOrder;
    }


}