package com.jtrio.zagzag.order;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.execption.OrderProductNotFoundException;
import com.jtrio.zagzag.execption.UserNotFoundException;
import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public OrderDto createOrder(OrderCommand command, Long userId) {
        Product orderProduct = productRepository.findById(command.getProductId()).orElseThrow(() ->
                new OrderProductNotFoundException("주문상품 없음"));
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("회원정보 없음"));
        ProductOrder productOrder = orderRepository.save(command.toOrder(orderProduct, user));
        return productOrder.toDto();

    }

}
