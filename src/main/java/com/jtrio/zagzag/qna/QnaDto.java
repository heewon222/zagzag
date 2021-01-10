package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.model.Qna;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QnaDto {
    private String userName;
    private String contents;
    private boolean secret;
    private LocalDateTime created;

    public static QnaDto toDto(Qna qna) {
        QnaDto qnaDto = new QnaDto();
        qnaDto.setUserName(qna.getUser().getEmail());
        qnaDto.setContents(qna.getContent());
        qnaDto.setSecret(qna.isSecret());
        qnaDto.setCreated(qna.getCreated());

        return qnaDto;
    }
}