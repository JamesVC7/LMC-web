package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection con;
    private static String bd="restaurante";
    private static String usuario="root";
    private static String passw="";
    private static String url="jdbc:mysql://localhost/"+bd;
    

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, passw);
        } catch (Exception e) {
            System.out.println("Error en la conexion BD");
        }
        return con;
    }
    
    public static void cerrar(){
        try{
            if (con !=null) {
                con.close();
            }
        }catch(Exception e){
            System.out.println("Error no se pudo cerrar la conexion a la BD");
        }
    }
    
    
        
}


