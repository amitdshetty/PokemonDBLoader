package com.amit.self;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * Parent Class loads the files that will needed throughout the application
 * New classes will have to extend this class to 
 * @author amitshetty
 *
 */
public class PokemonParentClass {
	
	private static final String CONFIG_FILE = "src/app.ini";
	public static Ini ini = null;
	public static Connection connection = null;
	
	static {
		try {
			if (ini == null) {
				ini = new Ini(new File(CONFIG_FILE));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found " + e.getMessage());
		}
	}
	
	protected static Connection establishDBConnection() {
		if(connection != null) {
			return connection;
		}
		MysqlDataSource mysqlDataSource = new MysqlDataSource();
		Section dbPokemonSection = ini.get("DB_Pokemon");
		Section dbConnectionParams = ini.get("DB_Connection_Params");
		try {
			mysqlDataSource.setLocalSocketAddress(dbConnectionParams.get("db.host"));
			mysqlDataSource.setPort(Integer.valueOf(dbConnectionParams.get("db.port")));
			mysqlDataSource.setUser(dbConnectionParams.get("db.username"));
			mysqlDataSource.setPassword(AppUtils.decodePassword(dbConnectionParams.get("db.password")));
			mysqlDataSource.setDatabaseName(dbPokemonSection.get("db.name"));
			// Server time zone setup is needed since the latest version of MySQL doesn't
			// support Eastern Daylight Time (EDT)
			mysqlDataSource.setServerTimezone(dbPokemonSection.get("db.server.timezone"));
			connection = mysqlDataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
