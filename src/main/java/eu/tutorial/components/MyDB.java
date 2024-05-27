package eu.tutorial.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eu.tutorial.objects.ObjScore;

/*
Class to interact with the DB
*/
public class MyDB {
    private String dbURL;
    private String dbDB;
    private String dbUSER;
    private String dbPASSWORD;
    private Connection connection;

    public MyDB(String idbRUL, String iDB, String iUser, String iPass ){
        // Some treatment
        dbURL = idbRUL;
        dbDB=iDB;
        dbUSER = iUser;
        dbPASSWORD = iPass;
        
        System.out.println("Connecting to database " + dbURL + " " + dbUSER);

        try{
            connection = DriverManager.getConnection(dbURL, dbUSER, dbPASSWORD);
            System.out.println("Connected to database " + dbURL + " " + dbUSER);
        }catch(Exception e){
            //TODO
            e.printStackTrace();
        }
    }

    public ObjScore getObjScore(String ipAddr){
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            // Create a simple query
            String sql = "SELECT * FROM ip_score_test  where IP_address = '" + ipAddr + "'";

            // Create a statement
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                // Retrieve data by column name
                int id = resultSet.getInt("id");
                String IP = resultSet.getString("IP_address");
                double score = resultSet.getDouble("score");
                if (ipAddr.compareToIgnoreCase(IP)==0){
                    return new ObjScore(id, IP, score);
                }
            }
        }catch(Exception e){
            //TODO:
        }finally{
            try{
                resultSet.close();
                preparedStatement.close();
            }catch(Exception e){
                //TODO:
            }
        }

        return null;
    }

    
    public boolean addUpdateScore(String ip, double iS){
        boolean res=false;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            // Create a simple query
            String sql = "UPDATE ip_score_test SET score = " + iS + " where IP_address = '" + ip + "'";

            // Create a statement
            preparedStatement = connection.prepareStatement(sql);
            int nRows  = preparedStatement.executeUpdate();
            System.out.println("Executed " + sql + " " + nRows);
            preparedStatement.close();

            //TODO: code could be better
            if (nRows == 0 ){ 
                sql = "INSERT INTO ip_score_test (IP_address, score) VALUES('" + ip+ "''," + iS +")" ;
                
                preparedStatement = connection.prepareStatement(sql);
                nRows  = preparedStatement.executeUpdate();
                System.out.println("Executed INS" + sql + " " + nRows);
                if (nRows ==1 ) res=true;
            }
            if (nRows == 1){res= true;}
            else res= false;
            
        }catch(Exception e){
            //TODO:
        }        
        return res;
    }
}

