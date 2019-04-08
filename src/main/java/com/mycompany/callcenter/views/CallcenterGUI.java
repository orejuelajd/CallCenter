package com.mycompany.callcenter.views;

import com.company.callcenter.dao.CallDAO;
import com.mycompany.callcenter.CallCreator;
import com.mycompany.callcenter.CallList;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

/**
 * Interfaz grafica
 */
public class CallcenterGUI extends JFrame implements ActionListener {

    JPanel employeesPanel;                          // Panel en el que se ponen los empleados
    JPanel callsPanel;                              // Panel en el que se ponen las llamadas pendientes
    EmployeePanel employeePanel;                    // Panel con la info de un empleado
    CallPanel callPanel;                            // Panel con la info de una llamada entrante
    HashMap<Integer, EmployeePanel> employeePanels; // Hashmap para almacenar los paneles de cada empleado con su id
    HashMap<Integer, CallPanel> callPanels;         // Hashmap para almacenar los paneles de las llamadas con su id
    private int y = 0;                              // Para ajuste en la interfaz
    JSplitPane splitPane;                           // Divisor en la interfaz
    private JTextField textfield1;                  // Textfield para colocar la duración de las llamadas creadas por boton
    
    /**
     * Constructor
     */
    public CallcenterGUI() {
        super("");
        setLayout(new BorderLayout());

        employeePanels = new HashMap<Integer, EmployeePanel>();
        callPanels = new HashMap<Integer, CallPanel>();

        this.employeesPanel = new JPanel();
        this.employeesPanel.setLayout(new GridBagLayout());

        this.callsPanel = new JPanel();
        this.callsPanel.setLayout(new GridBagLayout());

        JPanel panelBotones = new JPanel(new FlowLayout());
        textfield1 = new JTextField(5);
        JButton button = new JButton("Generar 11 Llamadas");
        button.addActionListener(this);
        panelBotones.add(new JLabel("Llamada de "));
        panelBotones.add(textfield1);
        panelBotones.add(new JLabel("segundos"));
        panelBotones.add(button);
        add(panelBotones, BorderLayout.SOUTH);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, employeesPanel, callsPanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(600);
        add(splitPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 500);
        setVisible(true);
    }
    
    /**
     * Para agregar un panel de empleado al panel de empleados
     * @param idEmployee id del empleado al que pertence la info
     */
    public void addEmployeePanel(int idEmployee) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = y;
        employeePanel = new EmployeePanel();
        this.employeesPanel.add(employeePanel, gbc);
        gbc.gridy = y++;
        this.employeesPanel.revalidate();
        validate();
        employeePanels.put(idEmployee, employeePanel);
    }
    
    /**
     * Para agregar un panel de llamada al panel de llamadas
     * @param idCall id de la llamada a la que pertenece la info
     */
    public void addCallPanel(int idCall) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = y;
        callPanel = new CallPanel();
        this.callsPanel.add(callPanel, gbc);
        gbc.gridy = y++;
        this.callsPanel.revalidate();
        validate();
        callPanels.put(idCall, callPanel);
    }
    
    /**
     * Obtener panel de empleados
     * @return jpanel de empleados
     */
    public JPanel getEmployeesPanel() {
        return employeesPanel;
    }
    
    /**
     * reemplazar panel de empleados
     * @param employeesPanel jpanel de empleados
     */
    public void setEmployeesPanel(JPanel employeesPanel) {
        this.employeesPanel = employeesPanel;
    }
    
    /**
     * obtener panel de llamadas
     * @return Jpanel de llamadas
     */
    public JPanel getCallsPanel() {
        return callsPanel;
    }
    
    /**
     * Reemplazar panel de llamadas
     * @param callsPanel Jpanel de llamadas
     */
    public void setCallsPanel(JPanel callsPanel) {
        this.callsPanel = callsPanel;
    }
    
    /**
     * Obtener panel de empleado
     * @return jpanel de empleado
     */
    public EmployeePanel getEmployeePanel() {
        return employeePanel;
    }
    
    /**
     * Reemplazar panel de empleado
     * @param employeePanel jpanel de empleado
     */
    public void setEmployeePanel(EmployeePanel employeePanel) {
        this.employeePanel = employeePanel;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Acciones al presionar boton
     * @param evt Evento
     */
    public void actionPerformed(ActionEvent evt) {
        if (!textfield1.getText().equals("")) {

            int duration = Integer.valueOf(textfield1.getText());
            int idCall = 0;
            for (int i = 0; i < 11; i++) {
                try {
                    idCall = CallDAO.saveCall(duration);
                } catch (SQLException ex) {
                    Logger.getLogger(CallCreator.class.getName()).log(Level.SEVERE, null, ex);
                }
                CallList.createCall(idCall, duration);

                this.addCallPanel(idCall);
                this.getCallPanels().get(idCall).getId().setText("#" + Integer.toString(idCall));
                this.getCallPanels().get(idCall).getDuracion_llamada().setText(textfield1.getText());
                System.out.println("(" + new Date() + ") Creando llamada. Duracion: " + textfield1.getText() + " s");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresa los segundos de duración de la llamada", "Mensaje", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Obtener hashmap de paneles de empleados
     * @return hashmap de paneles de empleados
     */
    public HashMap<Integer, EmployeePanel> getEmployeePanels() {
        return employeePanels;
    }
    
    /**
     * reemplazar hashmap de paneles de empleados
     * @param employeePanels hashmap de paneles de empleados
     */
    public void setEmployeePanels(HashMap<Integer, EmployeePanel> employeePanels) {
        this.employeePanels = employeePanels;
    }
    
    /**
     * obtener panel de llamada
     * @return jpanel de llamada
     */
    public CallPanel getCallPanel() {
        return callPanel;
    }
    
    /**
     * Reemplazar panel de llamada
     * @param callPanel Jpanel de llamada
     */
    public void setCallPanel(CallPanel callPanel) {
        this.callPanel = callPanel;
    }
    
    /**
     * Obtener hashmap de paneles de llamadas
     * @return hashmap de paneles de llamadas
     */
    public HashMap<Integer, CallPanel> getCallPanels() {
        return callPanels;
    }
    
    /**
     * Reemplazar hashmap de paneles de llamadas
     * @param callPanels hashmap de paneles de llamadas
     */
    public void setCallPanels(HashMap<Integer, CallPanel> callPanels) {
        this.callPanels = callPanels;
    }

}
