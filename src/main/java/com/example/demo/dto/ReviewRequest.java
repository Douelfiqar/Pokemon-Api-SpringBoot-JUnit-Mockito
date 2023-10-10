package com.example.demo.dto;

import com.example.demo.entities.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ReviewRequest {
    private String title;
    private String content;
    private int starts;
    private Long pokemonId;
}
