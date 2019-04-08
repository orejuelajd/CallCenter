# Call Center

A continuación se describe el funcionamiento general de la app para un callcenter:

# FUNCIONAMIENTO GENERAL

![alt text](https://raw.githubusercontent.com/orejuelajd/callcenter/master/resources/diagrama_general_callcenter.png)


La llamadas que van llegando al callcenter se van agregando a una cola en espera con un orden FIFO (First In, First Out), la clase Dispatcher mirará el empleado con cargo más bajo que se encuentre disponible para asignarle la primera llamada en la cola de llamadas pendientes. Luego de realizar la asignación, la clase pasará a buscar la siguiente llamada para asignarla a otro empleado libre y así sucesivamente. En el momento en que todos los empleados estén ocupados, la cola seguirá almacenando las llamadas en orden FIFO para que sean atendidas por los empleados cuando estos se desocupen, es por eso que el sistema en consola muestra "Total llamadas en espera: " mostrando la cantidad de llamadas en fila, con el fin de que el usuario sepa cuantas llamadas tienen que pasar primero para que lo atienda un empleado.

# CLASES

![Diagrama de clases](https://raw.githubusercontent.com/orejuelajd/callcenter/master/resources/uml_callcenter.png)

- **CallCenter:** Clase principal del programa. Inicia el creador de llamadas (CallCreator) y al despachador de llamadas (Dispatcher).

- **CallCreator:** Esta clase se encarga principalmente de la generación permanente de llamadas para que puedan ser atendidas por los empleados del callcenter.

- **Employee:** Clase encargada de almacenar la información sobre los empleados.

- **Dispatcher:** Clase encargada de  atender las llamadas y asignarlas a los empleados.

- **EmployeesList:** Clase usada para almacenar la lista de empleados.

- **Call:** Clase para llamada

- **CallList:** Clase para el almacenamiento de la llamadas en la cola

- **CallCenterTest (Test #1):** Test con 10 llamadas. Clase para Test unitario en el que llegan 10 llamadas al callcenter. En el callcenter hay 10 empleados, por lo que el sistema podrá atender las 10 llamadas al mismo tiempo.

- **CallCenterTest2 (Test #2):** Test con más de 10 llamadas, en este caso 11 llamadas. Clase para Test unitario en el que llegan 11 llamadas al callcenter. En el callcenter hay 10 empleados, por lo que el sistema para atender las 11 llamadas, atenderá la primeras 10 al mismo tiempo con los 10 empleados, y el primer empleado en terminar su respectiva llamada tomará la 11 llamada, teniendo en cuenta su cargo.

# BASE DE DATOS
Para la creación de la base de datos SQL ejecutar el archivo ´´´callcenteralmundo.sql´´´ (MySQL).
![Tabla de la base de datos](https://raw.githubusercontent.com/orejuelajd/callcenter/master/resources/tablas_base_datos.PNG)

# GUI
![Mockup](https://raw.githubusercontent.com/orejuelajd/callcenter/master/resources/mockup_gui.PNG)
![GUI](https://raw.githubusercontent.com/orejuelajd/callcenter/master/resources/gui.PNG)