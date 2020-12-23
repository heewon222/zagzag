package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.ProductOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderCommand command, @RequestParam Long userId) {
        return orderService.createOrder(command, userId);
    }

    @GetMapping
    public List<ProductOrder> getOrder(@RequestParam Long userId, @RequestParam LocalDate localDate, Pageable pageable) {
        return orderService.getOrder(userId, localDate, pageable);
    }
}
