package com.mycompany.callcenter.views;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase para el panel que tiene la info de la llamada entrante
 */
public class CallPanel extends JPanel {
    private JLabel id;                      // id de la llamada/panel
    private JLabel duracion_llamada;        // duracion de la llamada
    private JLabel id_label;                // label del id de la llamada
    private JLabel duracion_llamada_label;  // label de la duración de la llamada
    private JLabel llamada_atendida;        // Llamada atendida
    
    /**
     * Se ajusta el panel con los valores iniciales
     */
    public CallPanel() {
        super();
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new GridLayout(1,12,8,8));
        id_label = new JLabel("ID:");
        duracion_llamada_label = new JLabel("Duracion s.");
        id = new JLabel("--");
        duracion_llamada = new JLabel("--");
        llamada_atendida = new JLabel("");
        this.add(id_label);
        this.add(id);
        this.add(duracion_llamada_label);
        this.add(duracion_llamada);
        this.add(llamada_atendida);
    }
    
    /**
     * Obterner el label con el id de la llamada
     * @return label de id de la llamada
     */
    public JLabel getId() {
        return id;
    }
    
    /**
     * Ajustar el valor del label del id la llamada
     * @param id id de la llamada
     */
    public void setId(JLabel id) {
        this.id = id;
    }
    
    /**
     * Obtener label con duración de la llamada
     * @return label con duracion de la llamada
     */
    public JLabel getDuracion_llamada() {
        return duracion_llamada;
    }
    
    /**
     * Ajustar valor en label con duración de la llamada
     * @param duracion_llamada duración de la llamada
     */
    public void setDuracion_llamada(JLabel duracion_llamada) {
        this.duracion_llamada = duracion_llamada;
    }
    
    /**
     * Obtener texto de label con id de la llamada
     * @return texto en label
     */
    public JLabel getId_label() {
        return id_label;
    }
    
    /**
     * Ajustar texto de label con id de la llamada
     * @param id_label texto en label
     */
    public void setId_label(JLabel id_label) {
        this.id_label = id_label;
    }
    
    /**
     * Obtener texto con duración de la llamada
     * @return texto en label
     */
    public JLabel getDuracion_llamada_label() {
        return duracion_llamada_label;
    }
    
    /**
     * Ajustar texto con duración de la llamada
     * @param duracion_llamada_label texto en label
     */
    public void setDuracion_llamada_label(JLabel duracion_llamada_label) {
        this.duracion_llamada_label = duracion_llamada_label;
    }
    
    /**
     * Obtener texto en label de llamada atendida
     * @return texto en label del llamada atendida
     */
    public JLabel getLlamada_atendida() {
        return llamada_atendida;
    }
    
    /**
     * Ajustar texto en label del llamada atendida
     * @param llamada_atendida texto en label de llamada atendida
     */
    public void setLlamada_atendida(JLabel llamada_atendida) {
        this.llamada_atendida = llamada_atendida;
    }
}
