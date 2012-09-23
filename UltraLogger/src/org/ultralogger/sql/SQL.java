package org.ultralogger.sql;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.ultralogger.MainLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

public class SQL {

    public static final String driver = "com.mysql.jdbc.Driver";
    private static final Logger log = Logger.getLogger("Minecraft");
    private Connection conn;
    private MainLogger plugin;
    private String dbms = "mysql";
    private String serverName,portNumber,userName,password,dbName,prefix;

    /*
     * Enable Sql logging at plugin enable
     */

    public SQL(MainLogger head,String serverName,String portNumber,String userName,String password,String dbName,String prefix){
        this.plugin=head;
        this.serverName = serverName;
        this.portNumber = portNumber;
        this.userName = userName;
        this.password = password;
        this.dbName= dbName;
        this.prefix= prefix;
        connect();
    }

    /*
     * Connect to database and catch errors
     */

    public void connect(){
        try{
            Class.forName(driver);
            conn = this.getConnection();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Create if not exists and use db
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS "+dbName);
            stmt.executeQuery("USE "+dbName);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Establish connection and log to console
     */

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);
        conn = DriverManager.getConnection("jdbc:" + this.dbms + "://" + this.serverName + ":" + this.portNumber + "/",connectionProps);
        log.info("Connected to database");
        return conn;
    }

    /*
     * Execute update and print errors to console
     * Can also be used to create Tables
     * TODO: Might be better to buffer it into little query packets instead of loads of single query calls
     */

    public void query(String query){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
        } catch (SQLException e) {
            System.out.println("[UltraLogger] (ERROR) Can NOT execute query : "+query);
            e.printStackTrace();
        }
    }
    
    /*
     * Mysql_real_escape_string
     */
    public static String mysql_real_escape_string(java.sql.Connection link, String str) 
            throws Exception
      {
          if (str == null) {
              return null;
          }
                                      
          if (str.replaceAll("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/? ]","").length() < 1) {
              return str;
          }
              
          String clean_string = str;
          clean_string = clean_string.replaceAll("\\\\", "\\\\\\\\");
          clean_string = clean_string.replaceAll("\\n","\\\\n");
          clean_string = clean_string.replaceAll("\\r", "\\\\r");
          clean_string = clean_string.replaceAll("\\t", "\\\\t");
          clean_string = clean_string.replaceAll("\\00", "\\\\0");
          clean_string = clean_string.replaceAll("'", "\\\\'");
          clean_string = clean_string.replaceAll("\\\"", "\\\\\"");
                                                              
          if (clean_string.replaceAll("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/?\\\\\"' ]"
            ,"").length() < 1) 
          {
              return clean_string;
          }
                              
          java.sql.Statement stmt = link.createStatement();
          String qry = "SELECT QUOTE('"+clean_string+"')";
              
          stmt.executeQuery(qry);
          java.sql.ResultSet resultSet = stmt.getResultSet();
          resultSet.first();
          String r = resultSet.getString(1);
          return r.substring(1,r.length() - 1);       
      }

    /**
     * Escape data to protected against SQL Injection
     *
     * @param link
     * @param str
     * @return
     * @throws Exception 
     */
    
    public static String quote(java.sql.Connection link, String str)
          throws Exception
    {
        if (str == null) {
            return "NULL";
        }
        return "'"+mysql_real_escape_string(link,str)+"'";
    }
    
    /**
     * Escape identifier to protected against SQL Injection
     *
     * @param link
     * @param str
     * @return
     * @throws Exception 
     */
    
    public static String nameQuote(java.sql.Connection link, String str)
          throws Exception
    {
        if (str == null) {
            return "NULL";
        }
        return "`"+mysql_real_escape_string(link,str)+"`";
    }

    /*
     * Register listeners for the Loggers
     */
    public void register(Listener l){
        plugin.getServer().getPluginManager().registerEvents(l, plugin);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public String getprefix(){
        return prefix;
    }
}
