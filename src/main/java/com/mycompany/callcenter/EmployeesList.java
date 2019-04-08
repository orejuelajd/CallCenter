package com.mycompany.callcenter;

import com.mycompany.callcenter.views.CallcenterGUI;
import java.util.ArrayList;

/**
 * Clase usada para almacenar la lista de empleados.
 * @version 1.0
 */
public class EmployeesList {
    
    // Declaración de variables
    private static EmployeesList instance;          // Instancia de la clase
    private ArrayList<Employee> employeesList;
    
    /**
     * Constructor de la clase
     */
    public EmployeesList() {
        this.employeesList = new ArrayList();
    }
    
    /**
     * Para obtener la instacia de esta clase
     * @return Instancia de la clase EmployeesList
     */
    private static EmployeesList getInstance() {
        if (instance == null) {
            instance = new EmployeesList();
        }
        return instance;
    }
    
    /**
     * Agrega un empleado a la lista de empleados
     * @param id ID del empleado
     * @param position Cargo del empleado
     */
    public static void addEmployee(int id, String position) {
        Employee employee = new Employee(id, position);
        getInstance().employeesList.add(employee);
    }
    
    /**
     * Retorna la lista de empleados
     * @return Lista de empleados
     */
    public static ArrayList<Employee> getList() {
        ArrayList<Employee> list = getInstance().employeesList;
        return list;
    }
}
