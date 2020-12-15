package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder,Long> {
}
