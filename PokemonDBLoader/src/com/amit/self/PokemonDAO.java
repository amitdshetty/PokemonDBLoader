package com.amit.self;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ini4j.Profile.Section;

public class PokemonDAO extends PokemonParentClass {
	
	/**
	 * Method inserts records from csbv into the database using batch execution.
	 * Rollback option is available
	 * TBD : To increase efficiency use a stored procedure to do inserts and alter table to use name as Primary Key.
	 * If insert fails then it will be easy to handle it from SQL rather than have a middle man like JDBC deal with it.
	 * @param connection
	 * @param pokemonBeanList
	 * @param pokemonDBSection
	 */
	public void insertCSVDataTODB(List<PokemonBean> pokemonBeanList, Section pokemonDBSection) {
		PreparedStatement preparedStatement = null;
		try {
			Connection connection = establishDBConnection();
			connection.setAutoCommit(false);
			String insertSQLQuery = "INSERT INTO " + pokemonDBSection.get("db.pokemon.table.name") + 
					" (pokemon_name, pokemon_type1, pokemon_type2, pokemon_total_stats, pokemon_total_hp, pokemon_attack, pokemon_defense, " 
					+ "pokemon_sp_atk, pokemon_sp_def, pokemon_speed, pokemon_stage, pokemon_is_legendary) " 
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(insertSQLQuery);
			Iterator<PokemonBean> pokemonListIterator = pokemonBeanList.iterator();
			while(pokemonListIterator.hasNext()) {
				PokemonBean pokemonBean = pokemonListIterator.next();
				preparedStatement.setString(1, pokemonBean.getPokemonName());
				preparedStatement.setString(2,  pokemonBean.getType1());
				preparedStatement.setString(3, pokemonBean.getType2());
				preparedStatement.setInt(4, pokemonBean.getTotalStats());
				preparedStatement.setInt(5, pokemonBean.getTotalHP());
				preparedStatement.setInt(6, pokemonBean.getTotalAttack());
				preparedStatement.setInt(7, pokemonBean.getTotalDefense());
				preparedStatement.setInt(8, pokemonBean.getTotalSpAttack());
				preparedStatement.setInt(9, pokemonBean.getTotalSpDefense());
				preparedStatement.setInt(10, pokemonBean.getSpeed());
				preparedStatement.setInt(11, pokemonBean.getStage());
				preparedStatement.setBoolean(12, pokemonBean.isLegendary());
				
				preparedStatement.addBatch();
			}
			
			int[] numOfUpdates = preparedStatement.executeBatch();
			for(int i = 0; i< numOfUpdates.length; i++) {
				if(numOfUpdates[i] == -2) {
					System.out.println("Insertion number " + i + " : unknown number of rows updated");
				} else {
					System.out.println("Insertion number " + i + " successful: " + numOfUpdates[i] + " rows updated");
				}
			}
			connection.commit();
		} catch (SQLException sqle) {
			doRollback(connection);
			try {
	            // Make it back to default to avoid problems
				// Refer: https://stackoverflow.com/questions/14625371/rollback-batch-execution-when-using-jdbc-with-autocommit-true
	            connection.setAutoCommit(true);
	        } catch (SQLException ex1) {
	            ex1.printStackTrace();
	        }
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Perform rollback if execution fails
	 * @param connection
	 */
	private void doRollback(Connection connection) {
	    try {
	        connection.rollback();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	
	/**
	 * Method to test the connection between Java application and database 
	 * TBD: Write a Junit test case for the same
	 * 
	 * @param mysqlConnection
	 */
	@SuppressWarnings("unused")
	private void testExtraction(Connection mysqlConnection) {
		try {
			Statement stmt = mysqlConnection.createStatement();
			ResultSet resultSet = stmt.executeQuery("select host, user as time from user");
			List<TestUser> testUserList = new ArrayList<>();

			while (resultSet.next()) {
				String host = resultSet.getString("host");
				String user = resultSet.getString("time");

				TestUser testUser = new TestUser(user, host);
				testUserList.add(testUser);
			}

			System.out.println(testUserList.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
