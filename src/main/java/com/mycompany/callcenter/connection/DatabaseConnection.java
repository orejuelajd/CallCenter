package com.mycompany.callcenter.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    static String USER_DATABASE = "root";
    static String PASSWORD_DATABASE = "root";

    /**
     * Método para la conexión a la base de datos
     * @return Instancia de la conexión con la base de datos
     */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/callcenteralmundo", USER_DATABASE, PASSWORD_DATABASE);
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error --> + ex.getMessage()");
            return null;
        }
    }

    /**
     * Método para la cerrar la conexión con la base de datos
     * @param con Conexión con la base de datos
     */
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}
