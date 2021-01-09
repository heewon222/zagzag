package com.jtrio.zagzag.order;

import com.jtrio.zagzag.execption.OrderProductNotFoundException;
import com.jtrio.zagzag.execption.ProductNotFoundException;
import com.jtrio.zagzag.execption.ProductQuantityZeroException;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderDto createOrder(OrderCommand command, User user) {
        Product product = productRepository.findById(command.getProductId()).orElseThrow(() ->
                new OrderProductNotFoundException("주문상품 없음"));
        if (product.getQuantity() == 0) {
            throw new ProductQuantityZeroException("주문가능 수량 없음");
        }
        ProductOrder productOrder = orderRepository.save(command.toOrder(product, user));
        return OrderDto.toDto(productOrder);
    }

    //주문조회(기간..?)
    @Transactional
    public List<OrderDto> getOrder(User user, LocalDate localDate, Pageable pageable, Long productId) {
        productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException("주문상품 없음"));
        List<ProductOrder> orders = orderRepository.findByUserIdAndLocalDate(user.getId(), localDate, pageable, productId);
        List<OrderDto> orderDtos = new ArrayList<>();

        for (ProductOrder productOrder : orders) {
            OrderDto orderDto = OrderDto.toDto(productOrder);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    //주문취소
    public OrderDto deleteOrder(User user, Long orderId) {
        ProductOrder productOrder = orderRepository.findById(orderId).orElseThrow(() ->
                new OrderProductNotFoundException("주문상품 없음"));

        return OrderDto.toDto(productOrder);
    }
}
