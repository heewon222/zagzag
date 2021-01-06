package com.jtrio.zagzag.product;


import com.jtrio.zagzag.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;


    @PostMapping
    public ProductDto create(@Valid @RequestBody ProductCommand.CreateProduct product) {
        return productService.create(product);
    }

    @GetMapping
    public List<ProductDto> getByCategory(@RequestParam Long categoryId, Pageable pageable) {
        return productService.getByCategory(categoryId, pageable);
    }

    @GetMapping("/{id}")
    public ProductDto getProductInfo(@PathVariable Long productId) {
        return productService.getProductInfo(productId);
    }
}
