package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.security.SecurityUser;
import com.jtrio.zagzag.user.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public OrderDto createOrder(@RequestBody OrderCommand command, @AuthenticationPrincipal SecurityUser securityUser) {
        User user = userService.findById(securityUser.getUser().getId());
        return orderService.createOrder(command, user);
    }

    @GetMapping
    public List<OrderDto> getOrder(@AuthenticationPrincipal SecurityUser securityUser, @RequestParam LocalDate localDate, Pageable pageable, @PathVariable Long productId) {
        User user = userService.findById(securityUser.getUser().getId());
        return orderService.getOrder(user, localDate, pageable, productId);
    }

    @DeleteMapping("/{id}")
    public OrderDto deleteOrder(@AuthenticationPrincipal SecurityUser securityUser, @PathVariable Long id) {
        User user = userService.findById(securityUser.getUser().getId());
        return orderService.deleteOrder(user,id);
    }
}
