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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ReviewService {
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ReviewDto createReview(ReviewCommand.CreateReview command, User user, Long orderId) {
        ProductOrder productOrder = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderProductNotFoundException("주문상품 없음"));
        if (!productOrder.getUser().equals(user)) {
            throw new NotAllowedReviewWriteException("리뷰권한 없음");
        }
        Review review = reviewRepository.save(command.toReview(user, productOrder));
        Product product = productRepository.findById(productOrder.getProduct().getId()).orElseThrow(() ->
                new ProductNotFoundException("상품없음"));

        //평점
        product.setTotalScore(reviewRepository.avgProductTotalScore(product.getId()));
        return ReviewDto.toDto(review);
    }

    public List<ReviewDto> readReview(User user, Long orderId, Pageable pageable) {
        orderRepository.findById(orderId).orElseThrow(() ->
                new OrderProductNotFoundException("주문상품 없음"));

        List<ReviewDto> reviewDtos = new ArrayList<>();
        List<Review> reviews = reviewRepository.findByProductOrderId(orderId, pageable);

        for (Review review : reviews) {
            ReviewDto reviewDto = ReviewDto.toDto(review);
            reviewDtos.add(reviewDto);
        }
        return reviewDtos;
    }
}
