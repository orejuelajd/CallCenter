package com.mycompany.callcenter;

import com.mycompany.callcenter.views.CallcenterGUI;

/**
 * Clase encargada de almacenar la información sobre los empleados.
 * @author version 1.0
 */
public class Employee {

    // Declaración de variables
    private int id;                 //ID del empleado
    private boolean inCall;         //Si el empleado se encuentra atendiendo una llamada
    private long callEnd;           //Tiempo de finalización de la llamada
    private String position;        // Cargo que ocupa el empleado
    
    /**
     * Constructor
     * @param id id del empleado
     * @param position Cargo del empleado
     */
    public Employee(int id, String position) {
        this.id = id;
        this.position = position;
        this.inCall = false;
    }

    /**
     * Configura si el empleado está o no atendiendo una llamada
     *
     * @param inCall Boleano que indica si el empleado está atendiendo llamada
     */
    public void setInCall(boolean inCall) {
        this.inCall = inCall;
    }

    /**
     * Configura el tiempo de finalización de una llamada
     *
     * @param callEnd Long que indica el tiempo de finalización de una llamada
     */
    public void setCallEnd(long callEnd) {
        this.callEnd = callEnd;
    }

    /**
     * Entrega el ID del empleado
     *
     * @return ID del empleado
     */
    public int getId() {
        return id;
    }

    /**
     * Indica si el empleado está atendiendo una llamada
     *
     * @return Booleano que dice si el empleado está atendiendo una llamada.
     */
    public boolean isInCall() {
        return inCall;
    }

    /**
     * Indica el cargo del empleado
     *
     * @return Cargo del empleado.
     */
    public String getPosition() {
        return position;
    }

    public long getCallEnd() {
        return callEnd;
    }
}
