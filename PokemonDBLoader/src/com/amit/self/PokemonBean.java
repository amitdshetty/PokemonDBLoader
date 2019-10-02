package com.amit.self;

import com.opencsv.bean.CsvBindByPosition;

public class PokemonBean {
	
	@CsvBindByPosition(position = 1, required = true)
	private String pokemonName;
	
	@CsvBindByPosition(position = 2)
	private String type1;
	
	@CsvBindByPosition(position = 3)
	private String type2;
	
	@CsvBindByPosition(position = 4)
	private int totalStats;
	
	@CsvBindByPosition(position = 5)
	private int totalHP;
	
	@CsvBindByPosition(position = 6)
	private int totalAttack;
	
	@CsvBindByPosition(position = 7)
	private int totalDefense;
	
	@CsvBindByPosition(position = 8)
	private int totalSpAttack;
	
	@CsvBindByPosition(position = 9)
	private int totalSpDefense;
	
	@CsvBindByPosition(position = 10)
	private int speed;
	
	@CsvBindByPosition(position = 11)
	private int stage;
	
	@CsvBindByPosition(position = 12)
	private boolean isLegendary;

	/*
	 * Remember that parameterized constructor is not needed when using csvtoBean otherwise class will not load
	 * It will use the default constructor
	 */

	public String getPokemonName() {
		return pokemonName;
	}

	public void setPokemonName(String pokemonName) {
		this.pokemonName = pokemonName;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public int getTotalStats() {
		return totalStats;
	}

	public void setTotalStats(int totalStats) {
		this.totalStats = totalStats;
	}

	public int getTotalHP() {
		return totalHP;
	}

	public void setTotalHP(int totalHP) {
		this.totalHP = totalHP;
	}

	public int getTotalAttack() {
		return totalAttack;
	}

	public void setTotalAttack(int totalAttack) {
		this.totalAttack = totalAttack;
	}

	public int getTotalDefense() {
		return totalDefense;
	}

	public void setTotalDefense(int totalDefense) {
		this.totalDefense = totalDefense;
	}

	public int getTotalSpAttack() {
		return totalSpAttack;
	}

	public void setTotalSpAttack(int totalSpAttack) {
		this.totalSpAttack = totalSpAttack;
	}

	public int getTotalSpDefense() {
		return totalSpDefense;
	}

	public void setTotalSpDefense(int totalSpDefense) {
		this.totalSpDefense = totalSpDefense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public boolean isLegendary() {
		return isLegendary;
	}

	public void setLegendary(boolean isLegendary) {
		this.isLegendary = isLegendary;
	}

	@Override
	public String toString() {
		return "PokemonBean [pokemonName=" + pokemonName + ", type1=" + type1 + ", type2=" + type2 + ", totalStats="
				+ totalStats + ", totalHP=" + totalHP + ", totalAttack=" + totalAttack + ", totalDefense="
				+ totalDefense + ", totalSpAttack=" + totalSpAttack + ", totalSpDefense=" + totalSpDefense + ", speed="
				+ speed + ", stage=" + stage + ", isLegendary=" + isLegendary + "]";
	}

}
