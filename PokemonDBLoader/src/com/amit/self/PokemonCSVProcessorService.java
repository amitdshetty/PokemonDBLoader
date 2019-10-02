/**
 * 
 */
package com.amit.self;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ini4j.Profile.Section;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

/**
 * @author amitshetty
 *
 */
public class PokemonCSVProcessorService extends PokemonParentClass {
	
	public List<PokemonBean> readCsvFile() {

		List<PokemonBean> pokemonBeansList = null;

		try {
			Section filePatternSection = ini.get("File_Handler_Params");
			String fileName = filePatternSection.get("file.directory") + "/" + filePatternSection.get("file.name");
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			// To test if the file is being read correctly
			// String st;
			//
			// while ((st = br.readLine())!=null) {
			// System.out.println(st);
			// }
			ColumnPositionMappingStrategy<PokemonBean> ms = new ColumnPositionMappingStrategy<PokemonBean>();
			ms.setType(PokemonBean.class);
			CsvToBean<PokemonBean> cb = new CsvToBeanBuilder<PokemonBean>(br).withSkipLines(1).withMappingStrategy(ms)
					.build();

			pokemonBeansList = new ArrayList<PokemonBean>();
			Iterator<PokemonBean> pokemonIterator = cb.iterator();
			while (pokemonIterator.hasNext()) {
				PokemonBean pokemonBean = pokemonIterator.next();
				pokemonBeansList.add(pokemonBean);
			}

			System.out.println(pokemonBeansList.size());

		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return pokemonBeansList;

	}
}
