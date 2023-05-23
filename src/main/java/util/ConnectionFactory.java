/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author willi
 */
public class ConnectionFactory {
    
    public static String DRIVER = "com.mysql.jdbc.DRIVER";
    public static String URL = "jdbc:mysql://localhost:3306/todoapp";
    public static String USER = "root";
    public static String PASS = "";
    
        public static Connection getConnection(){
            try {
                Class.forName(DRIVER);
                return DriveManager.getConnection(URL, USER, PASS);
            } catch (Exception ex) {
                throw new RuntimeException("Erro com a conexão com o bando de dados: ", ex);    
            }
        }
        
         public static void closeConnection(Connection connection){
            try {
                if(connection != null){
                    connection.close();
                }
              
            } catch (Exception ex) {
                throw new RuntimeException("Erro ao fechar conexão com o bando de dados: ", ex);   
            }
        }
    
        public static void closeConnection(Connection connection, PreparedStatement statement){
            try {
                if(connection != null){
                    connection.close();
                }
                
                if(statement != null){
                    statement.close();
                }
            } catch (Exception ex) {
                throw new RuntimeException("Erro ao fechar conexão com o bando de dados: ", ex);   
            }
        }
        
        public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet){
            try {
                if(connection != null){
                    connection.close();
                }
                
                if(statement != null){
                    statement.close();
                }
                
                if(resultSet != null){
                    resultSet.close();
                }
                
            } catch (Exception ex) {
                throw new RuntimeException("Erro ao fechar conexão com o bando de dados: ", ex);   
            }
        }
    
    
}
