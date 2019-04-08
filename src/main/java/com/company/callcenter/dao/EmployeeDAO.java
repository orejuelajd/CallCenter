package com.company.callcenter.dao;

import com.mycompany.callcenter.Employee;
import com.mycompany.callcenter.connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    /**
     * Obtener la lista de empleados en la base de datos
     * @return List<Employee> Lista de empleados
     */
    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("SELECT id, position FROM employee ORDER BY id");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("position")
                );
                employees.add(employee);
            }
            return employees;
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
        } finally {
            DatabaseConnection.close(con);
        }
        return null;
    }
}
