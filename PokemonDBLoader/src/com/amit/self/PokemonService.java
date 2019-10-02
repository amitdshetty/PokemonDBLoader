/**
 * 
 */
package com.amit.self;

import java.util.List;

import org.ini4j.Profile.Section;

/**
 * @author amitshetty
 *
 */
public class PokemonService extends PokemonParentClass {
	
	/**
	 * Loads data from processed CSV file into SQL database
	 * @param pokemonBeanList
	 */
	public void insertDataIntoDB(List<PokemonBean> pokemonBeanList) {
		if (pokemonBeanList.size() > 0) {
			Section pokemonDBSection = ini.get("DB_Pokemon");
			PokemonDAO pokemonDao = new PokemonDAO();
			pokemonDao.insertCSVDataTODB(pokemonBeanList, pokemonDBSection);
		} else {
			System.out.println("No data to add to the database");
		}

	}

}
