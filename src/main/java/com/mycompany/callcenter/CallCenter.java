package com.mycompany.callcenter;

import com.mycompany.callcenter.views.CallcenterGUI;

/**
 * Clase principal del programa. Inicia el creador de llamadas (CallCreator)
 * y al despachador de llamadas (Dispatcher).
 * @version 1.0
 */
public class CallCenter {
    
    private static String WINDOW_TITLE = "Callcenter Almundo";
    
    public static void main(String args[]) { 
        // Inicialización de la capa de presentacion
        CallcenterGUI gui = new CallcenterGUI();
        gui.setVisible(true);
        gui.setTitle(WINDOW_TITLE);
        gui.setLocationRelativeTo(null);
        // Inicialización de la logica
        new CallCreator(gui).start();
        new Dispatcher(gui).start();
        new Finisher(gui).start();
    }
}
