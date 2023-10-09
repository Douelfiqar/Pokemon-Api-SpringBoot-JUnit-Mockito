package com.example.demo.services;

import com.example.demo.entities.Review;
import com.example.demo.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review updateReview(Long id, Review updatedReview) {
        // Check if the Review with the given ID exists
        Optional<Review> existingReviewOptional = reviewRepository.findById(id);
        if (existingReviewOptional.isPresent()) {
            Review existingReview = existingReviewOptional.get();
            // Update the existing Review with the new data
            existingReview.setTitle(updatedReview.getTitle());
            existingReview.setContent(updatedReview.getContent());
            existingReview.setStarts(updatedReview.getStarts());
            // You can update other fields as needed
            return reviewRepository.save(existingReview);
        } else {
            // Review with the given ID does not exist
            System.out.println("Review not found with ID: " + id);
        }
    }

    public void deleteReview(Long id) {
        // Check if the Review with the given ID exists
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
        } else {
            // Review with the given ID does not exist
            System.out.println("Review not found with ID: " + id);
        }
    }
}
