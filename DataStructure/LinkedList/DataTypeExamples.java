package DataStructure.LinkedList;

/**
 * Clase con ejemplos para poblar listas con enteros y contactos.
 * Sirve para probar el funcionamiento de la estructura de datos.
 */
public class DataTypeExamples {

    public static void testIntegers(LinkedList<Integer> lista) {
        lista.insertAtLastPosition(10);
        lista.insertAtLastPosition(22);
        lista.insertAtLastPosition(35);
        System.out.println("Ejemplo de lista de enteros:");
        lista.show();
    }

    public static void testComplexObjects(LinkedList<Contactos> lista) {
        lista.insertAtLastPosition(new Contactos("Diego Martinez", "Av. Universidad, Monterrey", "7865412001"));
        lista.insertAtLastPosition(new Contactos("Camila Perez", "Colonia Americana, Guadalajara", "5513024786"));
        lista.insertAtLastPosition(new Contactos("Armando Betancourt", "Av. Chipinque, Monterrey", "4320167550"));
        System.out.println("Ejemplo de lista de contactos:");
        lista.show();
    }
}
