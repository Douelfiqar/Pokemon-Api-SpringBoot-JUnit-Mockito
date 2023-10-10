package com.example.demo.mappers;

import com.example.demo.dto.PokemonRequest;
import com.example.demo.dto.PokemonResponse;
import com.example.demo.entities.Pokemon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PokemonMapper {
    PokemonMapper INSTANCE = Mappers.getMapper(PokemonMapper.class);

    @Mapping(target = "id", ignore = true)
    Pokemon pokemonRequestToPokemon(PokemonRequest pokemonRequest);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "reviewsList", target = "reviewsList")
    PokemonResponse pokemonToPokemonResponse(Pokemon pokemon);
}
