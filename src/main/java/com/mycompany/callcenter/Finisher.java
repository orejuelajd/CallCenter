package com.mycompany.callcenter;

import com.mycompany.callcenter.views.CallcenterGUI;
import java.awt.Color;
import java.util.List;

/**
 * Clase encargada de finalizar el estado de ocupado de los empleados de acuerdo
 * al tiempo en el que se acaban las llamadas.
 *
 * @version 1.0
 */
public class Finisher implements Runnable {

    // Declaración de variables
    private boolean runThread;
    static private CallcenterGUI gui;

    /**
     * Constructor de la clase
     */
    public Finisher(CallcenterGUI gui) {
        this.gui = gui;
    }

    /**
     * Hilo para la atención de llamadas
     */
    @Override
    public void run() {
        while (runThread) {
            finishCalls();
            pause();
        }
    }
    
    /**
     * Inicia la ejecución del hilo
     */
    public void start() {
        runThread = true;
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
    public static void finishCalls() {
        List<Employee> employees = EmployeesList.getList();
        for (Employee employee : employees) {
            if (System.currentTimeMillis() > employee.getCallEnd()) {
                Finisher.gui.getEmployeePanels().get(employee.getId()).getId_llamada().setText("--");
                Finisher.gui.getEmployeePanels().get(employee.getId()).getEstado().setForeground(Color.blue);
                Finisher.gui.getEmployeePanels().get(employee.getId()).getEstado().setText("Libre");
                Finisher.gui.getEmployeePanels().get(employee.getId()).getDuracion_llamada().setText("--");
                Finisher.gui.getEmployeePanels().get(employee.getId()).getInicio_llamada().setText("--");
                Finisher.gui.getEmployeePanels().get(employee.getId()).getFin_llamada().setText("--");
                employee.setInCall(false);
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
     * Si el hilo está corriendo
     * @return boolean
     */
    public boolean isRunThread() {
        return runThread;
    }
    
    /**
     * Ajustar el funcionamiento del hilo
     * @param runThread boolean
     */
    public void setRunThread(boolean runThread) {
        this.runThread = runThread;
    }
}
