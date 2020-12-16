package com.jtrio.zagzag.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/user/{id}")
    public ReviewDto createReview(@Valid @RequestBody ReviewCommand.CreateReview command, @RequestParam Long userId, @RequestParam Long orderId) {
        return reviewService.createReview(command, userId, orderId);
    }

}
