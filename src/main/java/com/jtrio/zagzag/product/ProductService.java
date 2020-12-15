package com.jtrio.zagzag.product;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.execption.CategoryNotFoundException;
import com.jtrio.zagzag.execption.ProductNameDuplicationException;
import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    //상품 저장
    public ProductDto create(ProductCommand.CreateProduct command){
        if (productRepository.existsByName(command.getName())) {
            throw new ProductNameDuplicationException("상품이름 중복");
        }
        Category category=categoryRepository.findById(command.getCategoryID()).orElseThrow(()->
                new CategoryNotFoundException("카테고리 없음"));
        Product product=productRepository.save(command.toProduct(category));
        return product.toDto();
    }

    //카테고리별 조회
   /* public List<Product> inquiryByCategory(Long categoryID){
        List<Product> product = productRepository.findByCategoryId(categoryID);

        return product;
    }*/


}
