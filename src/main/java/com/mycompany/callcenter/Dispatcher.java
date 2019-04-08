package com.mycompany.callcenter;

import com.company.callcenter.dao.CallDAO;
import com.company.callcenter.dao.EmployeeDAO;
import com.mycompany.callcenter.views.CallcenterGUI;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de atender las llamadas y asignarlas a los empleados.
 * @version 1.0
 */
public class Dispatcher implements Runnable {

    // Declaración de variables
    private boolean runThread;
    private static ArrayList<Employee> employeesList;   // Lista de empleados
    static private CallcenterGUI gui;                   // GUI

    // Booleano para no imprimir el mensaje "No hay llamadas" 
    // una cantidad de veces innecesaria.
    private static boolean flag = true;

    /**
     * Constructor de la clase
     */
    public Dispatcher(CallcenterGUI gui) {
        this.gui = gui;
    }

    /**
     * Hilo para la atención de llamadas
     */
    @Override
    public void run() {
        while (runThread) {
            dispacthCall();
            pause();
        }
    }

    /**
     * Con este método se obtiene el empleado con menor rango que no esté
     * atendiedo una llamada.
     *
     * @return Empleado libre
     */
    private static Employee findFreeEmployee() {
        if (employeesList == null) {
            employeesList = EmployeesList.getList();
        }
        
        // Orden en el que se buscara el empleado libre
        String positions[] = {"operador", "supervisor", "director"}; 
        for (int i = 0; i < positions.length; i++) {
            Iterator<Employee> it = employeesList.iterator();
            while (it.hasNext()) {
                Employee employee = it.next();
                if (employee.isInCall() == false && employee.getPosition().equals(positions[i])) {
                    return employee;
                }
            }
        }
        return null;
    }

    /**
     * Inicia la ejecución del hilo
     */
    public void start() {
        runThread = true;

        // Se agregan los empleados a la lista de empleados.
        // Tienen que ser agregados desde acá ya que al inicio no se contaba con base de datos.
        // Agregándolos de esta forma, se puede acceder a la misma lista desde otras clases.
        // Se obtiene la lista de empleados
        List<Employee> employees = EmployeeDAO.getEmployees();
        int counter = 1;
        // Se recorren la lista de empleados
        for (Employee employee : employees) {
            EmployeesList.addEmployee(employee.getId(), employee.getPosition());
            this.gui.addEmployeePanel(employee.getId());
            this.gui.getEmployeePanels().get(employee.getId()).getId().setText("#" + counter);
            this.gui.getEmployeePanels().get(employee.getId()).getCargo().setText(employee.getPosition());
            counter++;
        }
        employeesList = EmployeesList.getList();
        new Thread(this).start();
    }

    /**
     * Detiene la ejecución del hilo
     */
    public void stop() {
        runThread = false;
    }

    /**
     * Encargado de la atención de las llamadas y asignación de estas a los
     * empleados que se encuentren libres
     */
    public static void dispacthCall() {

        Employee employee = findFreeEmployee();

        if (employee != null) {
            Call call = CallList.takeCall();
            if (call != null) {
                Calendar now = Calendar.getInstance(TimeZone.getTimeZone("America/Bogota"));
                String start = now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND);
                String finish = now.get(Calendar.HOUR) + ":" + now.get(Calendar.MINUTE) + ":" + (now.get(Calendar.SECOND) + call.getDuration());
                // Se ajusta el empleado como ocupado y en que momento se desocupa
                employee.setInCall(true);
                employee.setCallEnd(System.currentTimeMillis() + (call.getDuration() * 1000));
                // Se ajustan los valores de la llamada
                call.setStart(start);
                call.setFinish(finish);
                call.setIdEmployee(employee.getId());
                // Se actualiza el panel del empleado que atenderá la llamada
                try {
                    Dispatcher.gui.getEmployeePanels().get(employee.getId()).getId_llamada().setText(Integer.toString(call.getId()));
                    Dispatcher.gui.getEmployeePanels().get(employee.getId()).getEstado().setForeground(Color.red);
                    Dispatcher.gui.getEmployeePanels().get(employee.getId()).getEstado().setText("Ocupado");
                    Dispatcher.gui.getEmployeePanels().get(employee.getId()).getDuracion_llamada().setText(Integer.toString(call.getDuration()));
                    Dispatcher.gui.getEmployeePanels().get(employee.getId()).getInicio_llamada().setText(start);
                    Dispatcher.gui.getEmployeePanels().get(employee.getId()).getFin_llamada().setText(finish);
                    Dispatcher.gui.getCallPanels().get(call.getId()).invalidate();
                    Dispatcher.gui.getCallPanels().get(call.getId()).setVisible(false);
                    Dispatcher.gui.getCallPanels().get(call.getId()).removeAll();
                } catch (Exception e) {
                    System.out.println("No hay GUI inicializada"); // Separar el pintado de la función propia del dispatch
                }

                try {
                    // Se actualiza la info de la llamada en la base de datos
                    CallDAO.updateCall(call.getId(), employee.getId(), start, finish);
                } catch (SQLException ex) {
                    Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("(" + new Date() + ") Empleado #" + employee.getId() + " (" + employee.getPosition() + ") Responde llamada #" + call.getId());
                flag = true;
            } else {
                //System.out.println("No hay llamadas");
            }
        } else {
            if (flag == true) {
                System.out.println("(" + new Date() + ") Todos los empleados están ocupados, si hay llamadas se pasan a lista de espera");
                flag = false;
            }
        }
    }

    /**
     * Pausa la ejecución del hilo por un determinado tiempo
     */
    private void pause() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }
    
    /**
     * Saber si el hilo está corriendo
     * @return hilo 
     */
    public boolean isRunThread() {
        return runThread;
    }
    
    /**
     * Ajustar el funcionamiento del hilo
     * @param runThread true o false
     */
    public void setRunThread(boolean runThread) {
        this.runThread = runThread;
    }
    
    /**
     * Obtener la lista de empleados
     * @return ArryaList<employee> Lista de empleados
     */
    public static ArrayList<Employee> getEmployeesList() {
        return employeesList;
    }
    
    /**
     * Set a la lista de empleados
     * @param employeesList ArrayList<Employee>
     */
    public static void setEmployeesList(ArrayList<Employee> employeesList) {
        Dispatcher.employeesList = employeesList;
    }
    
    /**
     * Saber si la bandera está activa
     * @return true o false
     */
    public static boolean isFlag() {
        return flag;
    }
    
    /**
     * Activar o desactivar la bandera
     * @param flag true o false
     */
    public static void setFlag(boolean flag) {
        Dispatcher.flag = flag;
    }
}
