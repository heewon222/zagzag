package com.jtrio.zagzag.review;

import com.jtrio.zagzag.execption.NotAllowedReviewWriteException;
import com.jtrio.zagzag.execption.OrderProductNotFoundException;
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
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ReviewDto createReview(ReviewCommand.CreateReview command, Long userId, Long orderId) {
        ProductOrder productOrder = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderProductNotFoundException("주문상품 없음"));
        if (!productOrder.getUser().getId().equals(userId)) {
            throw new NotAllowedReviewWriteException("리뷰권한 없음");
        }
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("회원 아님"));

        Review review = reviewRepository.save(command.toReview(user, productOrder));
        Product product = productRepository.findById(productOrder.getProduct().getId()).orElseThrow(() ->
                new ProductNotFoundException("상품없음"));

        review.setTotalScore(review.averageScore(command.getReviews()));//리뷰갯수?

        return ReviewDto.toDto(review);
    }
}
