package com.example.demo.mappers;

import com.example.demo.dto.PokemonResponse;
import com.example.demo.dto.ReviewRequest;
import com.example.demo.dto.ReviewResponse;
import com.example.demo.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
    @Mapping(target = "id", ignore = true)
    Review reviewRequestToReview(ReviewRequest reviewRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "starts", target = "starts")
    ReviewResponse reviewToReviewResponse(Review review);
}
