package com.mycompany.callcenter;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase para el almacenamiento de la llamadas en la cola
 * @version 1.0
 */
public class CallList {
    
    // Declaración de variables
    private static CallList instance;   // Instacia de esta clase CallList
    static Queue<Call> queue;           // Cola para el almacenamiento de las llamadas
    
    /**
     * Crea una llamada y la agrega a la cola de llamadas
     * @param duration Duración de la nueva llamada
     */
    public static void createCall(int idCall, int duration) {
        Call call = new Call(idCall, duration);
        getInstance().queue.add(call);
        System.out.println("(" + new Date() + ") Encolando llamada #" + call.getId() + ". Duracion: " + 
                call.getDuration() + " s. Total llamadas en espera: " + getInstance().queue.size());
    }
    
    /**
     * Toma una llamada de la cola en orden FIFO (First in, First out)
     * @return La primer llamada en la cola
     */
    public static Call takeCall() {
        Call call = getInstance().queue.poll();
        if (call != null) {
            System.out.println("(" + new Date() + ") Tomando llamada #" + call.getId());
        }
        return call;
    }
    
    /**
     * Obtiene la instancia de esta clase.
     * @return Instancia de la clase CallList
     */
    public static CallList getInstance() {
        if (instance == null) {
            instance = new CallList();
        }
        return instance;
    }
    
    /**
     * Constructor de la clase
     */
    private CallList() {
        this.queue = new LinkedList<>();
    }
    
    /**
     * Obtiene la cola de llamadas que no han sido atendidas aún
     * @return Cola de llamadas pendientes
     */
    public static Queue<Call> getCallList(){
        return CallList.queue;
    }
}
