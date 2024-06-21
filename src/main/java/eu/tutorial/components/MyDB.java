package eu.tutorial.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eu.tutorial.objects.ObjScore;

public class MyDB {
    private String dbURL;
    private String dbDB;
    private String dbUSER;
    private String dbPASSWORD;
    private Connection connection;

    public MyDB(String idbRUL, String iDB, String iUser, String iPass ){
        dbURL = idbRUL;
        dbDB = iDB;
        dbUSER = iUser;
        dbPASSWORD = iPass;

        System.out.println("Connecting to database " + dbURL + " " + dbUSER);

        try {
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            System.out.println("Connected to database " + dbURL + " " + dbUSER);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ObjScore getObjScore(String ipAddr) {
        return null; 
    }

    public boolean addUpdateIPScore(String ip, double iS) {
        boolean res = false;
        PreparedStatement preparedStatement = null;

        try {
            
            String sql = "INSERT INTO ip_score (IP_address, score) VALUES('" + ip + "'," + iS + ") ON DUPLICATE key UPDATE score = " + iS;
            System.out.println("To EXEC  " + sql );
          
            preparedStatement = connection.prepareStatement(sql);
            int nRows = preparedStatement.executeUpdate();
            System.out.println("Executed " + sql + " " + nRows);
            preparedStatement.close();

            if (nRows == 1) { res = true; }
            else { res = false; }

            ObjScore objScore = ObjScore.createWithIP(getIdForIP(ip), ip, iS);
           

        } catch(Exception e) {
            System.out.println("Error on insert");
            e.printStackTrace();
        }        
        return res;
    }

    public boolean addUpdateUSERScore(String src, double iS) {
        boolean res = false;
        PreparedStatement preparedStatement = null;

        try {
            
            String sql = "INSERT INTO user_score (user, score) VALUES('" + src + "'," + iS + ") ON DUPLICATE key UPDATE score = " + iS;
            System.out.println("To EXEC  " + sql );
            
            preparedStatement = connection.prepareStatement(sql);
            int nRows = preparedStatement.executeUpdate();
            System.out.println("Executed " + sql + " " + nRows);
            preparedStatement.close();

            if (nRows == 1) { res = true; }
            else { res = false; }

            ObjScore objScore = ObjScore.createWithSRC(getIdForSRC(src), src, iS);
            

        } catch(Exception e) {
            System.out.println("Error on insert");
            e.printStackTrace();
        }        
        return res;
    }

    private int getIdForIP(String ip) {
        return 1; 
    }

    private int getIdForSRC(String src) {
        return 2;
    }
}
