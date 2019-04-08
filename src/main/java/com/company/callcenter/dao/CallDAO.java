package com.company.callcenter.dao;

import com.mycompany.callcenter.connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CallDAO {
    
    /**
     * Guardar una llamada en la base de datos
     * @param duration Duracion en segundos de la llamada
     * @return id de la llamada realizada
     * @throws SQLException 
     */
    public static int saveCall(int duration) throws SQLException {
        
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("INSERT INTO calls(duration, timestamp) VALUES(?,CURRENT_TIMESTAMP)");
            ps.setInt(1, duration);
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Llamada de " + duration + " segundos almacenada");

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return (generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Error insertando llamada. No se pudo obtener el ID.");
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Login error saveCall(duration) -->" + ex.getMessage());
        } finally {
            DatabaseConnection.close(con);
        }
        return 0;
    }
    
    /**
     * Actualizar la información de una llamada
     * @param idCall Id de la llamada a actualizar
     * @param idEmployee Id del empleado que atiende la llamada
     * @param start Hora en la que la llamada es atendida por el empleado
     * @param finish Hora de finalización de la llamada
     * @throws SQLException 
     */
    public static void updateCall(int idCall, int idEmployee, String start, String finish) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("update calls set id_employee = ?, start = ?, finish = ? where id = ?");
            ps.setInt(1, idEmployee);
            ps.setString(2, start);
            ps.setString(3, finish);
            ps.setInt(4, idCall);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Llamada # " + idCall + " actualizada");
            }
        } catch (SQLException ex) {
            System.out.println("Login error updateCall -->" + ex.getMessage());
        } finally {
            DatabaseConnection.close(con);
        }
    }
}
