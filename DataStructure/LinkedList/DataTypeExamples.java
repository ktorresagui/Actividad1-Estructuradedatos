package DataStructure.LinkedList;

import DataStructure.Queue.Queue;
import DataStructure.Stack.Stack;

/**
 * Cargador de datos de ejemplo para pruebas.
 */
public class DataTypeExamples {

    /** Simula un historial inicial de comandos */
    public static void testStack(Stack<String> stack) {
        stack.push("git init");
        stack.push("npm install express");
        stack.push("docker-compose up -d");
        
    }

    /** Simula procesos en espera */
    public static void testQueue(Queue<String> queue) {
        queue.enqueue("Eclipse IDE");
        queue.enqueue("Discord");
        queue.enqueue("Steam");
        queue.enqueue("Adobe Photoshop");
    }
}
