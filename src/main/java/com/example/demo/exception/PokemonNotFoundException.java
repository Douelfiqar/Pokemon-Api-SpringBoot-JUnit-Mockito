package com.example.demo.exception;

public class PokemonNotFoundException extends Throwable {
    public PokemonNotFoundException(String s) {
        System.out.println(s);
    }
}
