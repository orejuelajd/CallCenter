
import com.company.callcenter.dao.CallDAO;
import com.mycompany.callcenter.Call;
import com.mycompany.callcenter.CallList;
import com.mycompany.callcenter.Dispatcher;
import com.mycompany.callcenter.Employee;
import com.mycompany.callcenter.EmployeesList;
import com.mycompany.callcenter.connection.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase para Test unitario en el que llegan 10 llamadas al callcenter. En el
 * el callcenter hay 10 empleados, por lo que el sistema podrá atender las 10
 * llamadas al mismo tiempo.
 * 
 * @version 1.0
 */
public class CallCenterTest {
    
    /**
     * Constructor
     */
    public CallCenterTest() {
    }
    
    /**
     * Test para probar la conexión a la base de datos
     */
    @Test
    public void databaseConnectionTest(){
        System.out.println("------------------ TEST CONEXIÓN BASE DATOS -----------------");
        Connection con = DatabaseConnection.getConnection();
        assertNotNull(con);
    }
    
    /**
     * Test para probar la inserción de llamadas a la base de datos
     */
    @Test
    public void saveCallTest(){
        System.out.println("------------------ TEST CONEXIÓN INSERCIÓN LLAMADA -----------------");
        int idCall = -1;
        try {
            idCall = CallDAO.saveCall(1); //Inserción de llamada de 1 segundo
        } catch (SQLException ex) {
            Logger.getLogger(CallCenterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotEquals(-1, idCall); // Prueba exitosa si ha retornado un ID
    }
    
    /**
     * Test para probar si se generan con éxito 10 llamadas y son agregadas
     * a la cola de llamadas pendientes
     */
    @Test
    public void createCallTest() {
        System.out.println("------------------ TEST CREACIÓN 10 LLAMADAS Y ADICIÓN A COLA DE LLAMADAS PENDIENTES -----------------");
        int MIN_DURATION_CALL = 5;
        int MAX_DURATION_CALL = 10;
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            // Adición de la llamada creada a la fila de llamadas para que
            // pueda ser atendida por un empleado
            CallList.createCall(i, random.nextInt(MAX_DURATION_CALL - MIN_DURATION_CALL) + MIN_DURATION_CALL); 
        }
        System.out.println("Llamadas en cola: " + CallList.getCallList().size());
        assertNotNull(CallList.getCallList());
    }
    
    /**
     * Test para probar que se toman llamadas de la fila de llamadas pendientes 
     */
    @Test
    public void takeCallTest(){
        System.out.println("------------------ TEST TOMA DE LLAMADA -----------------");
        int MIN_DURATION_CALL = 5;
        int MAX_DURATION_CALL = 10;
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            // Adición de la llamada creada a la fila de llamadas para que
            // pueda ser atendida por un empleado
            CallList.createCall(i, random.nextInt(MAX_DURATION_CALL - MIN_DURATION_CALL) + MIN_DURATION_CALL); 
        }
        System.out.println("Llamadas en cola: " + CallList.getCallList().size());
        int tamanioFilaLlamadasOriginal = CallList.getCallList().size();
        Call call = CallList.takeCall();
        System.out.println("Llamadas en cola depues de tomar 1: " + CallList.getCallList().size());
        int tamanioFilaLlamadasDespuesTomarLlamada = CallList.getCallList().size();
        assertNotEquals(tamanioFilaLlamadasOriginal, tamanioFilaLlamadasDespuesTomarLlamada);
    }
    
    /**
     * Test para probar la adición de empleados a la lista de empleados, si al
     * final la lista es nula, la operación ha sido fallida.
     */
    @Test
    public void addEmployeesTest() {
        System.out.println("------------------ TEST CREACION DE EMPLEADOS -----------------");
        EmployeesList.addEmployee(1, "operator");
        EmployeesList.addEmployee(2, "supervisor");
        EmployeesList.addEmployee(3, "director");
        EmployeesList.addEmployee(4, "operator");
        EmployeesList.addEmployee(5, "operator");
        EmployeesList.addEmployee(6, "operator");
        EmployeesList.addEmployee(7, "operator");
        EmployeesList.addEmployee(8, "operator");
        EmployeesList.addEmployee(9, "operator");
        EmployeesList.addEmployee(10, "operator");
        System.out.println("Lista de empleados agregados:" + EmployeesList.getList());
        assertNotNull(EmployeesList.getList());
    }
   
    /**
     * Test para probar la asignación de una llamada que se encuentre en la
     * cola de llamadas pendientes a un empleado. Se ejecuta el metodo dispatchCall
     * que toma una llamada de la lista de llamadas pendientes y se la asigna a un
     * empleado. Luego se recorre la lista de empleados que se llenó en la prueba
     * addEmployeesTest y se mira cuantos están ocupados, por lo que solo debe 
     * estar 1 empleado ocupado para verificar que se asignó bien esa llamada tomada.
     */
    @AfterClass
    public static void dipatchCallsTest() {
        System.out.println("------------------ TEST ASIGNACION DE LLAMADA A EMPLEADO -----------------");
        Dispatcher.dispacthCall();
        
        ArrayList<Employee> employeesList = EmployeesList.getList();
        int counterEmployeesBusy = 0;
        
        Iterator<Employee> it = employeesList.iterator();
        while (it.hasNext()) {
            Employee employee = it.next();
            if (employee.isInCall() == true) {
                counterEmployeesBusy++;
            }
        }
        
        System.out.println("Cantidad de Empleados Ocupados: " + counterEmployeesBusy);
        System.out.println("Total de Empleados: " + employeesList.size());
        assertEquals(1, counterEmployeesBusy);
    }
}
