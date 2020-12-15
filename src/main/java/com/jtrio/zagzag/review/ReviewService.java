package com.jtrio.zagzag.review;

import com.jtrio.zagzag.execption.ProductNotFoundException;
import com.jtrio.zagzag.execption.UserNotFoundException;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.order.OrderRepository;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;


    @Transactional
    public ReviewDto createReview(ReviewCommand.CreateReview command, Long userId){
        ProductOrder checkUser = orderRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("User 없음")); //상품산사람 조회...????
        User user = userRepository.findById(userId).orElseThrow(()->
                new UserNotFoundException("회원 아님"));
        Product product = productRepository.findById(command.getProductId()).orElseThrow(()->
                new ProductNotFoundException("상품정보 없음"));

        Review review = reviewRepository.save(command.toReview(user,product));

        return review.toDto();
    }
}
