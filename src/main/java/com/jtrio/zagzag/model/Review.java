package com.jtrio.zagzag.model;

import com.jtrio.zagzag.review.ReviewDto;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})//하이버네이트가 자동으로 채워줌
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;
    private String image;
    private byte score;
    private byte totalScore;
    private byte deliveryScore;
    private byte average;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToMany
    private List<User> likers;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private ProductOrder productOrder;

    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;

    public byte averageScore(List<Review> reviews) {
        int sumProductScore = 0;

        for (Review review : reviews) {
            byte score = review.getScore();
            sumProductScore += score;
        }
        return (byte) (sumProductScore / reviews.size());
    }
}
