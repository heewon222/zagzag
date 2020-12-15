package com.jtrio.zagzag.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("reviews/review")
    public ReviewDto createReview(@RequestBody ReviewCommand.CreateReview command, @RequestParam Long userId){
        return reviewService.createReview(command,userId);
    }

}
