package com.jtrio.zagzag.product;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.execption.CategoryNotFoundException;
import com.jtrio.zagzag.execption.ProductNotFoundException;
import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ReviewRepository reviewRepository;

    //상품 저장
    @Transactional
    public ProductDto create(ProductCommand.CreateProduct command) {
        Category category = categoryRepository.findById(command.getCategoryID()).orElseThrow(() ->
                new CategoryNotFoundException("카테고리 없음"));
        Product product = productRepository.save(command.toProduct(category));
        return ProductDto.toDto(product);
    }

    //카테고리별 조회
    public List<ProductDto> getByCategory(Long categoryId, Pageable pageable) {

        return productRepository.findByCategoryId(categoryId, pageable);
    }

    //주문상세 조회
    @Transactional
    public ProductDto getProductInfo(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException("상품정보 없음"));
        return ProductDto.toDto(product);
    }

}
