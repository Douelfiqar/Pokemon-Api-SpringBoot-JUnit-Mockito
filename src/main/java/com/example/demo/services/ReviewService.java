package com.example.demo.services;

import com.example.demo.dto.PokemonRequest;
import com.example.demo.dto.PokemonResponse;
import com.example.demo.dto.ReviewRequest;
import com.example.demo.dto.ReviewResponse;
import com.example.demo.entities.Pokemon;
import com.example.demo.entities.Review;
import com.example.demo.exception.PokemonNotFoundException;
import com.example.demo.mappers.PokemonMapper;
import com.example.demo.mappers.ReviewMapper;
import com.example.demo.repositories.PokemonRepository;
import com.example.demo.repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;
    public ReviewResponse saveReview(ReviewRequest reviewRequest) {
        Review review = ReviewMapper.INSTANCE.reviewRequestToReview(reviewRequest);

        Pokemon pokemon = pokemonRepository.findById(reviewRequest.getPokemonId()).get();
        review.setPokemon(pokemon);

        Review savedReview = reviewRepository.save(review);

        return ReviewMapper.INSTANCE.reviewToReviewResponse(savedReview);
    }

    public Optional<ReviewResponse> getReviewById(Long id) throws PokemonNotFoundException {
        Optional<Review> reviewOptional =  reviewRepository.findById(id);
        if(reviewOptional.isPresent()){
            Review review = reviewOptional.get();
            ReviewResponse reviewResponse = ReviewMapper.INSTANCE.reviewToReviewResponse(review);
            return Optional.of(reviewResponse);
        }
        throw new PokemonNotFoundException("Review not found with ID"+id);
    }

    public List<ReviewResponse> getAllReviews() {
        List<Review> reviewList =  reviewRepository.findAll();
        List<ReviewResponse> reviewResponses = reviewList.stream().map(ReviewMapper.INSTANCE::reviewToReviewResponse).collect(Collectors.toList());
        return reviewResponses;
    }

    public ReviewResponse updateReview(Long id, ReviewRequest updatedReview) throws PokemonNotFoundException {

        Optional<Review> existingReviewOptional = reviewRepository.findById(id);
        if (existingReviewOptional.isPresent()) {
            Review existingReview = existingReviewOptional.get();

            existingReview.setContent(updatedReview.getContent());
            existingReview.setTitle(updatedReview.getTitle());
            existingReview.setStarts(updatedReview.getStarts());
            Review review = reviewRepository.save(existingReview);

            return ReviewMapper.INSTANCE.reviewToReviewResponse(review);
        }

        throw new PokemonNotFoundException("Review not found with ID"+id);


    }

    public void deleteReview(Long id) throws PokemonNotFoundException {
        // Check if the Pokemon with the given ID exists
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
        }
        throw new PokemonNotFoundException("Review not found with ID"+id);
    }
}
