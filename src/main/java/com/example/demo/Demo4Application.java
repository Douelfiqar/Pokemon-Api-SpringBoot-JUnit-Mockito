package com.example.demo;

import com.example.demo.dto.PokemonRequest;
import com.example.demo.dto.ReviewRequest;
import com.example.demo.repositories.PokemonRepository;
import com.example.demo.services.PokemonService;
import com.example.demo.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Demo4Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo4Application.class, args);
	}

	@Autowired
	PokemonService pokemonService;
	@Autowired
	ReviewService reviewService;
	@Bean
	CommandLineRunner start(){
		return args -> {
			PokemonRequest pokemonRequest1 = PokemonRequest.builder().name("Tyrantrum").type("Dragon").build();
			pokemonService.savePokemon(pokemonRequest1);
			PokemonRequest pokemonRequest2 = PokemonRequest.builder().name("Cresselia").type("Psychic").build();
			pokemonService.savePokemon(pokemonRequest2);
			PokemonRequest pokemonRequest3 = PokemonRequest.builder().name("Cramorant").type("Flying").build();
			pokemonService.savePokemon(pokemonRequest3);
			PokemonRequest pokemonRequest4 = PokemonRequest.builder().name("Bisharp").type("Steel").build();
			pokemonService.savePokemon(pokemonRequest4);
			PokemonRequest pokemonRequest5 = PokemonRequest.builder().name("Pickachu").type("Electric").build();
			pokemonService.savePokemon(pokemonRequest5);

			ReviewRequest reviewRequest = ReviewRequest.builder().title("Remarque").content("This Pokémon is from about 100,000,000 years ago.").starts(2).pokemonId(1L).build();
			ReviewRequest reviewRequest1 = ReviewRequest.builder().title("comment").content("It has the presence of a king, vicious but magnificent.").starts(1).pokemonId(1L).build();

			reviewService.saveReview(reviewRequest);
			reviewService.saveReview(reviewRequest1);
		};
	}
}
