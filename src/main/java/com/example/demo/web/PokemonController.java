package com.example.demo.web;

import com.example.demo.dto.PokemonRequest;
import com.example.demo.dto.PokemonResponse;
import com.example.demo.exception.PokemonNotFoundException;
import com.example.demo.services.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokemon")
@AllArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;


    @PostMapping
    public ResponseEntity<PokemonResponse> createPokemon(@RequestBody PokemonRequest pokemonRequest) {
        PokemonResponse createdPokemon = pokemonService.savePokemon(pokemonRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPokemon);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponse> getPokemonById(@PathVariable Long id) throws PokemonNotFoundException {
        Optional<PokemonResponse> pokemonResponseOptional = pokemonService.getPokemonById(id);
        if(pokemonResponseOptional.isPresent())
            return ResponseEntity.ok(pokemonResponseOptional.get());
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PokemonResponse>> getAllPokemons() {
        List<PokemonResponse> pokemonList = pokemonService.getAllPokemons();
        return ResponseEntity.ok(pokemonList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonResponse> updatePokemon(
            @PathVariable Long id,
            @RequestBody PokemonRequest updatedPokemonRequest) {
        try {
            PokemonResponse updatedPokemon = pokemonService.updatePokemon(id, updatedPokemonRequest);
            return ResponseEntity.ok(updatedPokemon);
        } catch (PokemonNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        try {
            pokemonService.deletePokemon(id);
            return ResponseEntity.noContent().build();
        } catch (PokemonNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
