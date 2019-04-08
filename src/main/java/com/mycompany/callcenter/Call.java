package com.mycompany.callcenter;

import java.io.Serializable;

/**
 * Clase para llamada
 *
 * @aversion 1.0
 */
public class Call implements Serializable {

    // Declaración de variables
    private int duration;   // Duración en tiempo de la llamada
    private int id;         // ID de la llamada
    private String start;   // Hora de inicio de la llamada
    private String finish;  // Hora de finalización de la llamada
    private int idEmployee; // Id del empleado que atiende la llamada
    
    /**
     * Obtener el id del empleado que atiende la llamada
     * @return Id del empleado
     */
    public Integer getIdEmployee() {
        return idEmployee;
    }
    
    /**
     * Ajustar el id del empleado que atiende la llamada
     * @param idEmployee Id del empleado
     */
    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
    
    /**
     * Obtener la hora de inicio de la llamada
     * @return String con hora de inicio de la llamada
     */
    public String getStart() {
        return start;
    }
    
    /**
     * Ajustar la hora de inicio de llamada
     * @param start hora de inicio de la llamada
     */
    public void setStart(String start) {
        this.start = start;
    }
    
    /**
     * Obtener la hora de finalización de la llamada
     * @return hora de fin de la llamada
     */
    public String getFinish() {
        return finish;
    }
    
    /**
     * Ajustar la hora de fin de la llamada
     * @param finish hora de fin de la llamada
     */
    public void setFinish(String finish) {
        this.finish = finish;
    }

    /**
     * Constructor de la clase
     *
     * @param id ID de la llamada
     * @param duration Duración de la llamada
     */
    public Call(int id, int duration) {
        this.id = id;
        this.duration = duration;
    }

    /**
     * Retorna la duración de la llamada
     * @return Duración de la llamada
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Retorna el ID de la llamada
     * @return ID de la llamada
     */
    public int getId() {
        return id;
    }

    /**
     * Configura el valor de la duración de la llamada
     * @param duration Duracion de la llamada
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Configura el valor del ID de la llamada
     * @param id Identificador de la llamada
     */
    public void setId(int id) {
        this.id = id;
    }
}
