package com.mycompany.callcenter.views;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * Clase para el panel que tiene la info de un empleado
 */
public class EmployeePanel extends JPanel {
    
    private JLabel id;                      // Label con id de empleado
    private JLabel cargo;                   // Label con cargo de empleado
    private JLabel estado;                  // Label con estado de empleado
    private JLabel id_llamada;              // Label con id de la llamada que atiende
    private JLabel duracion_llamada;        // Label con duración de la llamada
    private JLabel inicio_llamada;          // Label con hora de inicio de la llamada
    private JLabel fin_llamada;             // Label con hora de fin de la llamada
    private JLabel id_label;                // Label con texto predefinido
    private JLabel cargo_label;             // Label con texto predefinido
    private JLabel estado_label;            // Label con texto predefinido
    private JLabel id_llamada_label;        // Label con texto predefinido
    private JLabel duracion_llamada_label;  // Label con texto predefinido
    private JLabel inicio_llamada_label;    // Label con texto predefinido
    private JLabel fin_llamada_label;       // Label con texto predefinido
    
    /**
     * Constructor
     */
    public EmployeePanel() {
        super();
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new GridLayout(2,10,8,8));
        id_label = new JLabel("ID:");
        cargo_label = new JLabel("Cargo:");
        estado_label = new JLabel("Estado:");
        id_llamada_label = new JLabel("ID Llamada:");
        duracion_llamada_label = new JLabel("Duracion s.");
        inicio_llamada_label = new JLabel("Inicio:");
        fin_llamada_label = new JLabel("Fin:");
        id = new JLabel("--");
        cargo = new JLabel("--");
        estado = new JLabel("--");
        id_llamada = new JLabel("--");
        duracion_llamada = new JLabel("--");
        inicio_llamada = new JLabel("--");
        fin_llamada = new JLabel("--");
        this.add(id);
        this.add(cargo_label);
        this.add(cargo);
        this.add(estado_label);
        this.add(estado);
        this.add(id_llamada_label);
        this.add(id_llamada);
        this.add(duracion_llamada_label);
        this.add(duracion_llamada);
        this.add(inicio_llamada_label);
        this.add(inicio_llamada);
        this.add(fin_llamada_label);
        this.add(fin_llamada);
    }
    
    /**
     * get texto con id del empleado
     * @return Jlabel
     */
    public JLabel getId() {
        return id;
    }
    
    /**
     * reemplazar texto con id del empleado
     * @param id jlabel
     */
    public void setId(JLabel id) {
        this.id = id;
    }
    
    /**
     * Obtener texto con cargo del empleado
     * @return Jlabel
     */
    public JLabel getCargo() {
        return cargo;
    }
    
    /**
     * Reemplazar texto con cargo del empleado
     * @param cargo jlabel
     */
    public void setCargo(JLabel cargo) {
        this.cargo = cargo;
    }
    
    /**
     * Obtener texto con estado del empleado
     * @return jlabel
     */
    public JLabel getEstado() {
        return estado;
    }
    
    /**
     * Reemplazar label con estado del empleado
     * @param estado jlabel
     */
    public void setEstado(JLabel estado) {
        this.estado = estado;
    }
    
    /**
     * obtener label con id de la llamada
     * @return label con id de la llamada
     */
    public JLabel getId_llamada() {
        return id_llamada;
    }
    
    /**
     * Reemplazar label con id de la llamada
     * @param id_llamada jlabel
     */
    public void setId_llamada(JLabel id_llamada) {
        this.id_llamada = id_llamada;
    }
    
    /**
     * Obtener label con inicio de la llamada
     * @return jlabel
     */
    public JLabel getInicio_llamada() {
        return inicio_llamada;
    }
    
    /**
     * Reemplazar label con inicio de la llamada
     * @param inicio_llamada jlabel
     */
    public void setInicio_llamada(JLabel inicio_llamada) {
        this.inicio_llamada = inicio_llamada;
    }
    
    /**
     * Obtener label con fin de la llamada
     * @return jlabel
     */
    public JLabel getFin_llamada() {
        return fin_llamada;
    }
    
    /**
     * Reemplazar label con fin de la llamada
     * @param fin_llamada 
     */
    public void setFin_llamada(JLabel fin_llamada) {
        this.fin_llamada = fin_llamada;
    }
    
    public JLabel getId_label() {
        return id_label;
    }
    
    public void setId_label(JLabel id_label) {
        this.id_label = id_label;
    }
    
    public JLabel getCargo_label() {
        return cargo_label;
    }
    
    public void setCargo_label(JLabel cargo_label) {
        this.cargo_label = cargo_label;
    }
    
    public JLabel getEstado_label() {
        return estado_label;
    }
    
    public void setEstado_label(JLabel estado_label) {
        this.estado_label = estado_label;
    }
    
    public JLabel getId_llamada_label() {
        return id_llamada_label;
    }
    
    public void setId_llamada_label(JLabel id_llamada_label) {
        this.id_llamada_label = id_llamada_label;
    }
    
    public JLabel getInicio_llamada_label() {
        return inicio_llamada_label;
    }
    
    public void setInicio_llamada_label(JLabel inicio_llamada_label) {
        this.inicio_llamada_label = inicio_llamada_label;
    }
    
    public JLabel getFin_llamada_label() {
        return fin_llamada_label;
    }
    
    public void setFin_llamada_label(JLabel fin_llamada_label) {
        this.fin_llamada_label = fin_llamada_label;
    }
    
    public JLabel getDuracion_llamada() {
        return duracion_llamada;
    }
    
    public void setDuracion_llamada(JLabel duracion_llamada) {
        this.duracion_llamada = duracion_llamada;
    }
    
    public JLabel getDuracion_llamada_label() {
        return duracion_llamada_label;
    }
    
    public void setDuracion_llamada_label(JLabel duracion_llamada_label) {
        this.duracion_llamada_label = duracion_llamada_label;
    }
}
