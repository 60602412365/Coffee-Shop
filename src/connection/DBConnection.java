/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author PC
 */
public class DBConnection {
    private String dbname;
    private String userid;
    private String userpwd;
    
    private String host;
    private String port;

    public DBConnection() {
        BufferedReader instream;
        try {
            instream = Files.newBufferedReader(Paths.get("src/textfile/databaseinfo.txt"));
            LineNumberReader in = new LineNumberReader(instream);
            
            in.readLine();                          // bỏ line trạng thái đầu tiên của file
            this.dbname = in.readLine();
            this.userid = in.readLine();
            this.userpwd = in.readLine();
            this.host = in.readLine();
            this.port = in.readLine();
            
            in.close();
            instream.close();
        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DBConnection(String dbname, String userid, String userpwd, String host, String port) {
        this.dbname = dbname;
        this.userid = userid;
        this.userpwd = userpwd;
        this.host = host;
        this.port = port;
    }
    
    
    public Connection getCon() throws SQLException
    {
        Connection cn = null;
        
        try
        {
            //1. nap driver jdbc4
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            //2. tao ket noi den co so du lieu
            String url = "jdbc:sqlserver://" + host + ":" + port + "; database = " + dbname;
            cn = DriverManager.getConnection(url, userid, userpwd);
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Database: " + this.dbname + " in hostip: " + this.host + " can not connect!");
            ex.getStackTrace();
            
            
            // try another connect
            this.dbname = "COFFEESHOP";
            this.userid = "";
            this.userpwd = "";
            this.host = "";
            this.port = "";
            try{
                //1. nap driver jdbc4
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

                //2. tao ket noi den co so du lieu
                String url = "jdbc:sqlserver://" + host + ":" + port + "; database = " + dbname;
                cn = DriverManager.getConnection(url, userid, userpwd);
            }catch (ClassNotFoundException ex2) {
                System.out.println("Database: " + this.dbname + " in hostip: " + this.host + " can not connect!");
                ex2.getStackTrace();
            }
        }
        
        return cn;
    }
}