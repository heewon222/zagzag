package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.Qna;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class QnaCommand {

    @NotBlank
    private String contents;
    @NotBlank
    private Long productId;
    @NotBlank
    private boolean secret;

    public Qna CreateQna(User user, Product product) {
        Qna qna = new Qna();
        qna.setUser(user);
        qna.setProduct(product);
        qna.setContent(contents);
        qna.setSecret(secret);

        return qna;
    }

    public Qna UpdateQna(User user, Qna qna) {
        qna.setUser(user);
        qna.setProduct(qna.getProduct());
        qna.setContent(contents);
        qna.setSecret(secret);

        return qna;
    }
}