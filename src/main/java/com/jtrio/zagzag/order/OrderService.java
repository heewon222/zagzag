package com.jtrio.zagzag.order;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.execption.OrderProductNotFoundException;
import com.jtrio.zagzag.execption.ProductQuantityZeroException;
import com.jtrio.zagzag.execption.UserNotFoundException;
import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderDto createOrder(OrderCommand command, Long userId) {
        Product product = productRepository.findById(command.getProductId()).orElseThrow(() ->
                new OrderProductNotFoundException("주문상품 없음"));
        if (product.getQuantity() == 0) {
            throw new ProductQuantityZeroException("주문가능 수량 없음");
        }
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("회원정보 없음"));
        ProductOrder productOrder = orderRepository.save(command.toOrder(product, user));
        return OrderDto.toDto(productOrder);


    }

    //주문조회(기간..?)
    public List<OrderDto> getOrder(Long userId, LocalDate localDate, Pageable pageable) {
        userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("회원정보 없음"));
        List<OrderDto> orders = orderRepository.findByUserIdAndLocalDate(userId, localDate, pageable);


        return orders;
    }
}
