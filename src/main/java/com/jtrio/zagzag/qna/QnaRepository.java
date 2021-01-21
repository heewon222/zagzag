package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.model.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface QnaRepository extends JpaRepository<Qna, Long> {
    List<Qna> findByProductId(Long productId, Pageable pageable);
}