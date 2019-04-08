package com.mycompany.callcenter;

import com.company.callcenter.dao.CallDAO;
import com.mycompany.callcenter.views.CallcenterGUI;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase se encarga principalmente de la generación permanente de
 * llamadas para que puedan ser atendidas por los empleados del callcenter.
 * @version 1.0
 */
public class CallCreator implements Runnable {
    
    // Declaración de variables y constantes necesarias
    private Random random;
    private boolean runThread;
    private final int MIN_DURATION_CALL = 5; // Duración minima en segundos de una llamada
    private final int MAX_DURATION_CALL = 10; // Duración máxima en segundos de una llamada
    private final int SLEEP_INTERVAL_CALLS = 10; // Intervalo de tiempo para la generación de una nueva llamada
    private CallcenterGUI gui;
    
    /**
     * Constructor de la clase
     */
    public CallCreator(CallcenterGUI gui) {
        random = new Random();
        this.gui = gui;
    }
    
    /**
     * Hilo encargado de la creación de las llamadas
     */
    @Override
    public void run() {
        while (runThread) {
            int duration = random.nextInt(MAX_DURATION_CALL - MIN_DURATION_CALL) + MIN_DURATION_CALL;
            int idCall = 0;
            try {
                // Agrega una nueva llamada en la base de datos
                idCall = CallDAO.saveCall(duration);
            } catch (SQLException ex) {
                Logger.getLogger(CallCreator.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Crea una nueva llamada en la lista de llamada 
            // con el id de la llamada recien creada en la base de datos
            CallList.createCall(idCall, duration);
            // Se crea un panel con la info de la llamada creada 
            // en la cola de llamadas entrantes
            this.gui.addCallPanel(idCall);
            this.gui.getCallPanels().get(idCall).getId().setText("#" + Integer.toString(idCall));
            this.gui.getCallPanels().get(idCall).getDuracion_llamada().setText(Integer.toString(duration)); 
            System.out.println("(" + new Date() + ") Creando llamada. Duracion: " + duration + " s");
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
     * Pausa la ejecución del hilo por un tiempo definido
     */
    private void pause() {
        try {
            int sleep = random.nextInt(SLEEP_INTERVAL_CALLS);
            System.out.println("(" + new Date() + ") CallCreator duerme " + sleep + " s");
            Thread.sleep(sleep * 1000);
        } catch (InterruptedException e) {
        }
    }
}
