package com.example.demo.web;

import com.example.demo.dto.PokemonRequest;
import com.example.demo.dto.PokemonResponse;
import com.example.demo.dto.ReviewRequest;
import com.example.demo.dto.ReviewResponse;
import com.example.demo.exception.PokemonNotFoundException;
import com.example.demo.services.PokemonService;
import com.example.demo.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/review")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;


    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest reviewRequest) {
        ReviewResponse reviewResponse = reviewService.saveReview(reviewRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Long id) throws PokemonNotFoundException {
        Optional<ReviewResponse> reviewResponseOptional = reviewService.getReviewById(id);
        if(reviewResponseOptional.isPresent())
            return ResponseEntity.ok(reviewResponseOptional.get());
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAllReviews() {
        List<ReviewResponse> reviewResponseList = reviewService.getAllReviews();
        return ResponseEntity.ok(reviewResponseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewRequest updatedReviewRequest) {
        try {
            ReviewResponse updateReview = reviewService.updateReview(id, updatedReviewRequest);
            return ResponseEntity.ok(updateReview);
        } catch (PokemonNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.noContent().build();
        } catch (PokemonNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
