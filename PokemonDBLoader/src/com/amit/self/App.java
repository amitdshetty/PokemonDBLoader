package com.amit.self;

import java.util.List;

public class App {

	public static void main(String[] args) {
		PokemonCSVProcessorService pokemonCSVProcessorService = new PokemonCSVProcessorService();
		List<PokemonBean> pokemonBeanList = pokemonCSVProcessorService.readCsvFile();
		PokemonService pokemonService = new PokemonService();
		pokemonService.insertDataIntoDB(pokemonBeanList);
	}
}
