package org.justyce.sql;

import java.sql.*;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.justyce.MainLogger;

public class SQLManager {
	public static final String driver = "com.mysql.jdbc.Driver";
	
	private String host,user,pass,db;
	
	private Connection main;
	private MainLogger plugin;
	
	public SQLManager(MainLogger head,String host,String user,String pass,String db){
		this.plugin=head;
		setHost(host);
		setUser(user);
		setPass(pass);
		setDb(db);
		connect();
	}
	
	private void connect(){
		try {
			Class.forName(driver);
			main = (Connection) DriverManager.getConnection("jdbc:mysql://"+host,user,pass);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Create the def db 
		Statement stmt = null;
		try {
			stmt = main.createStatement();
			stmt.executeUpdate("CREATE DATABASE "+db);
			stmt.close();
		} catch (SQLException e) {
			//database already existing
		}
		//Using the def db 
		stmt = null;
		try {
			stmt = main.createStatement();
			stmt.executeQuery("USE "+db);
			stmt.close();
		} catch (SQLException e) {
			System.out.println("[UltraLogger] (ERROR) Can NOT execute query : "+"USE "+db);
			e.printStackTrace();
		}
	}
	
	public void register(Listener l){
		plugin.getServer().getPluginManager().registerEvents(l, plugin);
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	/**Execute an an updating query but show the error to the user
	 * 
	 * @param query
	 */
	public void query(String query){
		Statement stmt = null;
		try {
			stmt = main.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.out.println("[UltraLogger] (ERROR) Can NOT execute query : "+query);
			e.printStackTrace();
		}
	}
	/**Execute an an updating query but don't show the error to the user
	 * because this will print errors if the database is already created and it's not necessary
	 * @param query
	 */
	public void queryU(String query){
		Statement stmt = null;
		try {
			stmt = main.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
		}
	}

	public JavaPlugin getPlugin() {
		return plugin;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

}
