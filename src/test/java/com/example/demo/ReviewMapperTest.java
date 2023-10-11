package com.example.demo;

import com.example.demo.dto.ReviewRequest;
import com.example.demo.dto.ReviewResponse;
import com.example.demo.entities.Pokemon;
import com.example.demo.entities.Review;
import com.example.demo.mappers.PokemonMapper;
import com.example.demo.mappers.ReviewMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewMapperTest {
    @Test
    public void shouldMapReviewRequestToReview(){
        ReviewRequest reviewRequest = ReviewRequest.builder().title("Delcatty").content("Delcatty prefers to live an unfettered existence in which it can do as it pleases at its own pace.").starts(3).build();

        Review review = ReviewMapper.INSTANCE.reviewRequestToReview(reviewRequest);

        Assertions.assertThat(review).isNotNull();
        Assertions.assertThat(review.getTitle()).isEqualTo("Delcatty");
        Assertions.assertThat(review.getContent()).isEqualTo("Delcatty prefers to live an unfettered existence in which it can do as it pleases at its own pace.");
    }
    @Test
    public void shouldMapReviewToReviewResponse(){
        Review review = Review.builder().title("Review title").content("Content of review").starts(3).build();
        ReviewResponse reviewResponse = ReviewMapper.INSTANCE.reviewToReviewResponse(review);

        Assertions.assertThat(reviewResponse).isNotNull();
        Assertions.assertThat(reviewResponse.getTitle()).isEqualTo("Review title");
        Assertions.assertThat(reviewResponse.getContent()).isEqualTo("Content of review");
        Assertions.assertThat(reviewResponse.getStarts()).isEqualTo(review.getStarts());
    }
}
