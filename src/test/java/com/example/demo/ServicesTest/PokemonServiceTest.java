package com.example.demo.ServicesTest;

import com.example.demo.dto.PokemonRequest;
import com.example.demo.dto.PokemonResponse;
import com.example.demo.entities.Pokemon;
import com.example.demo.exception.PokemonNotFoundException;
import com.example.demo.mappers.PokemonMapper;
import com.example.demo.repositories.PokemonRepository;
import com.example.demo.services.PokemonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTest {
    @Mock
    private PokemonRepository pokemonRepository;
    @Mock
    private PokemonMapper pokemonMapper;
    @InjectMocks
    private PokemonService pokemonService;

    @Test
    public void savePokemon_returnPokemonResponse_Test(){
        PokemonRequest pokemonRequest = PokemonRequest.builder().name("Pikachu").type("Electric").build();

        Mockito.when(pokemonRepository.save(PokemonMapper.INSTANCE.pokemonRequestToPokemon(pokemonRequest))).thenReturn(PokemonMapper.INSTANCE.pokemonRequestToPokemon(pokemonRequest));

        Pokemon savedPokemon = pokemonRepository.save(PokemonMapper.INSTANCE.pokemonRequestToPokemon(pokemonRequest));

        Assertions.assertThat(savedPokemon).isNotNull();
        Assertions.assertThat(savedPokemon.getName()).isEqualTo("Pikachu");
        Assertions.assertThat(savedPokemon.getType()).isEqualTo("Electric");
    }

    @Test
    public void getPokemonById_returnPokemonResponse_Test() throws PokemonNotFoundException {
        Pokemon pokemon = Pokemon.builder().id(1L).name("Pikachu").type("Electric").build();
        PokemonResponse pokemonResponse1 = PokemonResponse.builder().name("Pikachu").type("Electric").build();

        Mockito.when(pokemonRepository.findById(1L)).thenReturn(Optional.of(pokemon));
        //Mockito.when(pokemonMapper.pokemonToPokemonResponse(pokemon)).thenReturn(pokemonResponse1);

        Optional<PokemonResponse> pokemonResponseOptional = pokemonService.getPokemonById(1L);

        Assertions.assertThat(pokemonResponseOptional).isNotNull();
    }
    @Test
    public void getAllPokemons_returnPokemonsResponse_Test(){
        ArrayList<Pokemon> pokemonArrayList = new ArrayList<>();
        pokemonArrayList.add(Pokemon.builder().name("Pikachu").type("Electric").build());
        Mockito.when(pokemonRepository.findAll()).thenReturn(pokemonArrayList);

        List<PokemonResponse> pokemonResponseList = pokemonService.getAllPokemons();

        Assertions.assertThat(pokemonResponseList).hasSize(1);
    }

    @Test
    public void updatePokemon_returnPokemonResponse_Test() throws PokemonNotFoundException{
        Pokemon pokemon = Pokemon.builder().id(1L).name("Pikachu").type("Electric").reviewsList(new ArrayList<>()).build();
        Mockito.when(pokemonRepository.findById(1L)).thenReturn(Optional.of(pokemon));
        Pokemon pokemon1 = Pokemon.builder().name("Pikachu2").type("Feu").reviewsList(new ArrayList<>()).build();
        Mockito.when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon1);

        PokemonRequest pokemonRequest = PokemonRequest.builder().name("Pikachu").type("Electric").build();
        PokemonResponse pokemonResponse = pokemonService.updatePokemon(1L, pokemonRequest);

        Assertions.assertThat(pokemonResponse).isNotNull();
        Assertions.assertThat(pokemonResponse.getName()).isEqualTo("Pikachu2");
        Assertions.assertThat(pokemonResponse.getType()).isEqualTo("Feu");
    }
    @Test
    public void deletePokemon_returnPokemonResponse_Test(){

    }
}
