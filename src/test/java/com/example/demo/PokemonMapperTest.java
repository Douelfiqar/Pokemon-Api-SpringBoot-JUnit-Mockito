package com.example.demo;

import com.example.demo.dto.PokemonRequest;
import com.example.demo.dto.PokemonResponse;
import com.example.demo.entities.Pokemon;
import com.example.demo.mappers.PokemonMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PokemonMapperTest {
    @Test
    public void shouldMapPokemonRequestToPokemon(){
        PokemonRequest pokemonRequest = PokemonRequest.builder().name("Tyrantrum").type("Dragon").build();
        Pokemon pokemon = PokemonMapper.INSTANCE.pokemonRequestToPokemon(pokemonRequest);
        Assertions.assertThat(pokemon).isNotNull();
        Assertions.assertThat(pokemon.getName()).isEqualTo("Tyrantrum");
        Assertions.assertThat(pokemon.getType()).isEqualTo("Dragon");
    }
    @Test
    public void shouldMapPokemonToPokemonResponse(){
        Pokemon pokemon = Pokemon.builder().name("Tyrantrum").type("Dragon").build();
        PokemonResponse pokemonResponse = PokemonMapper.INSTANCE.pokemonToPokemonResponse(pokemon);
        Assertions.assertThat(pokemonResponse).isNotNull();
        Assertions.assertThat(pokemonResponse.getName()).isEqualTo("Tyrantrum");
        Assertions.assertThat(pokemonResponse.getType()).isEqualTo("Dragon");
    }
}
