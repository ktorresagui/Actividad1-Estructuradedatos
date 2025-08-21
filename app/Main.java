package app;

import DataStructure.LinkedList.DataTypeExamples;
import DataStructure.Stack.Stack;
import DataStructure.Queue.Queue;
import java.util.Scanner;

/**
 *  Simulador de gestor de tareas
 *  Permite registrar comandos, consultar historial, lanzar instrucciones y organizar procesos
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Crear estructuras de datos
        Stack<String> historial = new Stack<>();
        Queue<String> colaProcesos = new Queue<>();
        int opcion;

        // Precargar datos de ejemplo
        DataTypeExamples.testStack(historial);
        DataTypeExamples.testQueue(colaProcesos);

        do {
            System.out.println("\n==================================");
            System.out.println("     SISTEMA OPERATIVO BASICO       ");
            System.out.println("==================================");
            System.out.println("1. Registrar un nuevo comando - Stack");
            System.out.println("2. Consultar historial de comandos - Stack");
            System.out.println("3. Lanzar el último comando - Stack");
            System.out.println("4. Incorporar proceso a la cola - Queue");
            System.out.println("5. Atender el próximo proceso - Queue");
            System.out.println("6. Mostrar la cola de procesos - Queue");
            System.out.println("7. Finalizar simulador");
            System.out.println("==================================");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número válido.");
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.print("Escribe el comando: ");
                    String cmd = sc.nextLine().trim();
                    if (!cmd.isEmpty()) {
                        historial.push(cmd);
                        System.out.println("Comando registrado en historial.");
                    } else {
                        System.out.println("No se puede registrar un comando vacío.");
                    }
                }
                case 2 -> {
                    System.out.println("Historial de comandos:");
                    historial.show();
                }
                case 3 -> {
                    if (!historial.isEmpty()) {
                        String cmd = historial.pop();
                        System.out.println("Ejecutando: " + cmd);
                    } else {
                        System.out.println("No hay comandos disponibles.");
                    }
                }
                case 4 -> {
                    System.out.print("Ingresa el nombre del proceso: ");
                    String proc = sc.nextLine().trim();
                    if (!proc.isEmpty()) {
                        colaProcesos.enqueue(proc);
                        System.out.println("Proceso incorporado a la cola.");
                    } else {
                        System.out.println("No se puede agregar un proceso vacío.");
                    }
                }
                case 5 -> {
                    if (!colaProcesos.isEmpty()) {
                        String proc = colaProcesos.dequeue();
                        System.out.println("Procesando: " + proc);
                    } else {
                        System.out.println("No hay procesos en espera.");
                    }
                }
                case 6 -> {
                    System.out.println("Cola de procesos:");
                    colaProcesos.show();
                }
                case 7 -> System.out.println("Gracias por usar el sistema. Hasta pronto.");
                default -> {
                    if (opcion != -1)
                        System.out.println("Opción inválida, intenta nuevamente.");
                }
            }

        } while (opcion != 7);

        sc.close();
    }
}
