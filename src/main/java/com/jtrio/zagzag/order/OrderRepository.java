package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder, Long> {
    List<OrderDto> findByUserIdAndLocalDate(Long userId, LocalDate localDate, Pageable pageable);
}
