package com.example.demo.services;

import com.example.demo.dto.PokemonRequest;
import com.example.demo.dto.PokemonResponse;
import com.example.demo.entities.Pokemon;
import com.example.demo.exception.PokemonNotFoundException;
import com.example.demo.mappers.PokemonMapper;
import com.example.demo.repositories.PokemonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PokemonService {

    private PokemonRepository pokemonRepository;

    public PokemonResponse savePokemon(PokemonRequest pokemonRequest) {
        Pokemon pokemon = PokemonMapper.INSTANCE.pokemonRequestToPokemon(pokemonRequest);
        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        return PokemonMapper.INSTANCE.pokemonToPokemonResponse(savedPokemon);
    }

    public Optional<PokemonResponse> getPokemonById(Long id) throws PokemonNotFoundException {
        Optional<Pokemon> pokemonOptional =  pokemonRepository.findById(id);
        if(pokemonOptional.isPresent()){
            Pokemon pokemon = pokemonOptional.get();
            PokemonResponse pokemonResponse = PokemonMapper.INSTANCE.pokemonToPokemonResponse(pokemon);
            return Optional.of(pokemonResponse);
        }
        throw new PokemonNotFoundException("Pokemon not found with ID"+id);
    }

    public List<PokemonResponse> getAllPokemons() {
        List<Pokemon> pokemonList =  pokemonRepository.findAll();
        List<PokemonResponse> pokemonResponseList = pokemonList.stream().map(PokemonMapper.INSTANCE::pokemonToPokemonResponse).collect(Collectors.toList());
        return pokemonResponseList;
    }

    public PokemonResponse updatePokemon(Long id, PokemonRequest updatedPokemon) throws PokemonNotFoundException {

        Optional<Pokemon> existingPokemonOptional = pokemonRepository.findById(id);
        if (existingPokemonOptional.isPresent()) {
            Pokemon existingPokemon = existingPokemonOptional.get();

            existingPokemon.setName(updatedPokemon.getName());
            existingPokemon.setType(updatedPokemon.getType());
           Pokemon pokemon = pokemonRepository.save(existingPokemon);

            return PokemonMapper.INSTANCE.pokemonToPokemonResponse(pokemon);
        }

        throw new PokemonNotFoundException("Pokemon not found with ID"+id);


    }

    public void deletePokemon(Long id) throws PokemonNotFoundException {
        // Check if the Pokemon with the given ID exists
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.deleteById(id);
        }
        throw new PokemonNotFoundException("Pokemon not found with ID"+id);
    }
}
