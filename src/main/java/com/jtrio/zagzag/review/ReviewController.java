package com.jtrio.zagzag.review;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ReviewDto createReview(@Valid @RequestBody ReviewCommand.CreateReview command, @RequestParam Long userId, @RequestBody Long orderId) {
        return reviewService.createReview(command, userId, orderId);
    }

}
