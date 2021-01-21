package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.execption.NotAllowedDeleteException;
import com.jtrio.zagzag.execption.OrderProductNotFoundException;
import com.jtrio.zagzag.execption.QnaNotFoundException;
import com.jtrio.zagzag.execption.UserNotFoundException;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.Qna;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.order.OrderRepository;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QnaService {
    private final QnaRepository qnaRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public QnaDto createQna(QnaCommand qnaCommand, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(qnaCommand.getProductId()).orElseThrow(() ->
                new OrderProductNotFoundException("주문상품 없음"));
        userRepository.findById(user.getId()).orElseThrow(() ->
                new UserNotFoundException("회원정보 없음"));
        Qna qna = qnaRepository.save(qnaCommand.CreateQna(user, product));

        return QnaDto.toDto(qna);
    }

    public List<QnaDto> readQna(Long userId, Long productId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow();
        productRepository.findById(productId).orElseThrow(() ->
                new OrderProductNotFoundException("주문상품 없음"));
        List<Qna> qnas = qnaRepository.findByProductId(productId, pageable);
        List<QnaDto> qnaDtos = new ArrayList<>();

        for (Qna qna : qnas) {
            QnaDto qnaDto = QnaDto.toDto(qna);
            qnaDtos.add(qnaDto);
        }

        return qnaDtos;
    }

    public QnaDto updateQna(Long id, QnaCommand qnaCommand, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Qna qna = qnaRepository.findById(id).orElseThrow(() ->
                new QnaNotFoundException("QnA 없음"));
        if (!qna.getUser().equals(user)) {
            throw new NotAllowedDeleteException("삭제 권한 없음");
        }
        qnaRepository.save(qnaCommand.UpdateQna(user, qna));

        return QnaDto.toDto(qna);
    }

    public QnaDto deleteQna(Long userId, Long id) {
        User user = userRepository.findById(userId).orElseThrow();
        Qna qna = qnaRepository.findById(id).orElseThrow(() ->
                new QnaNotFoundException("QnA조회 없음"));
        if (!qna.getUser().equals(user)) {
            throw new NotAllowedDeleteException("삭제 권한 없음");
        }

        return QnaDto.toDto(qna);
    }
}