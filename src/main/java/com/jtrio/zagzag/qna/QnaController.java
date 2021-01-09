package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.security.SecurityUser;
import com.jtrio.zagzag.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("qnas")
public class QnaController {
    private final QnaService qnaService;
    private final UserService userService;

    @PostMapping
    public QnaDto createQna(@Valid @RequestBody QnaCommand qnaCommand, @AuthenticationPrincipal SecurityUser securityUser) {
        User user = userService.findById(securityUser.getUser().getId());

        return qnaService.createQna(qnaCommand, user);
    }

    @GetMapping
    public List<QnaDto> readQna(@AuthenticationPrincipal SecurityUser securityUser, @RequestParam Long productId, Pageable pageable) {
        User user = userService.findById(securityUser.getUser().getId());

        return qnaService.readQna(user, productId, pageable);
    }

    @PutMapping("/{id}")
    public QnaDto updateQna(@RequestBody Long id, @AuthenticationPrincipal SecurityUser securityUser, @Valid @RequestBody QnaCommand qnaCommand) {
        User user = userService.findById(securityUser.getUser().getId());

        return qnaService.updateQna(id, qnaCommand, user);
    }

    @DeleteMapping("/{id}")
    public QnaDto deleteQna(@AuthenticationPrincipal SecurityUser securityUser, @PathVariable Long id) {
        User user = userService.findById(securityUser.getUser().getId());

        return qnaService.deleteQna(user, id);
    }
}
