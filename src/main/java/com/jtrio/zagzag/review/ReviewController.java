package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.security.SecurityUser;
import com.jtrio.zagzag.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;

    @PostMapping
    public ReviewDto createReview(@Valid @RequestBody ReviewCommand.CreateReview command, @AuthenticationPrincipal SecurityUser securityUser, @RequestBody Long orderId) {
        User user = userService.findById(securityUser.getUser().getId());

        return reviewService.createReview(command, user, orderId);
    }

    @GetMapping
    public List<ReviewDto> readReview(@AuthenticationPrincipal SecurityUser securityUser, @RequestParam Long orderId, Pageable pageable){
        User user = userService.findById(securityUser.getUser().getId());

        return reviewService.readReview(user, orderId, pageable);
    }
}
