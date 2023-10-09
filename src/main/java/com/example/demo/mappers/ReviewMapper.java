package com.example.demo.mappers;

import com.example.demo.dto.ReviewRequest;
import com.example.demo.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    Review ISTANCE = Mappers.getMapper(Review.class);
    @Mapping(target = "id", ignore = true)
    Review reviewRequestToReview(ReviewRequest reviewRequest);
}
