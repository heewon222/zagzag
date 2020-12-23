package com.jtrio.zagzag.model;

import com.jtrio.zagzag.order.OrderDto;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer price;
    private String address;
    private String productName;
    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
