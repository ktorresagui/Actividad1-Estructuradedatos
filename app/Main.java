package app;

import DataStructure.LinkedList.Contactos;
import DataStructure.LinkedList.DataTypeExamples;
import DataStructure.LinkedList.LinkedList;
import java.util.Scanner;

public class Main {

    /** Listas de contactos según el tipo */
    private static LinkedList<Contactos> listaSimple = null;
    private static LinkedList<Contactos> listaDoble = null;
    private static LinkedList<Contactos> listaCircular = null;

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int eleccion;

        do {
            mostrarMenuPrincipal();
            eleccion = leerOpcion(entrada);

            switch (eleccion) {
                case 1 -> menuContactos(entrada);
                case 2 -> menuEjemplos(entrada);
                case 3 -> {
                    String salir;
                    do {
                        System.out.print("Seguro que quieres salir? (s/n): ");
                        salir = entrada.nextLine().trim().toLowerCase();
                        if (!salir.equals("s") && !salir.equals("n")) {
                            System.out.println("Entrada invalida. Solo se permite 's' o 'n'.");
                        }
                    } while (!salir.equals("s") && !salir.equals("n"));

                    if (salir.equals("s")) {
                        eleccion = -99; // bandera de salida
                        System.out.println("Programa finalizado. Hasta pronto");
                    }
                }
                default -> System.out.println("Opcion invalida, intenta nuevamente.");
            }
        } while (eleccion != -99);

        entrada.close();
    }

    /** Menú principal */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n===== SISTEMA DE LISTAS =====");
        System.out.println("1. Manejar listas de Contactos");
        System.out.println("2. Probar ejemplos de listas");
        System.out.println("3. Salir");
        System.out.print("Selecciona una opcion: ");
    }

    /** Menú para contactos */
    private static void menuContactos(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- MENU DE CONTACTOS ---");
            System.out.println("1. Lista simple");
            System.out.println("2. Lista doble");
            System.out.println("3. Lista circular");
            System.out.println("4. Volver");
            System.out.print("Opcion: ");
            opcion = leerOpcion(sc);

            switch (opcion) {
                case 1 -> manejarContactos(sc, false, false);
                case 2 -> manejarContactos(sc, true, false);
                case 3 -> manejarContactos(sc, true, true);
                case 4 -> System.out.println("Regresando al menu principal...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 4);
    }

    /** Menú para ejemplos */
    private static void menuEjemplos(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- MENU DE EJEMPLOS ---");
            System.out.println("1. Ejemplos de lista simple");
            System.out.println("2. Ejemplos de lista doble");
            System.out.println("3. Ejemplos de ista circular");
            System.out.println("4. Volver");
            System.out.print("Opcion: ");
            opcion = leerOpcion(sc);

            switch (opcion) {
                case 1 -> ejemplosEnteros(sc, false, false);
                case 2 -> ejemplosEnteros(sc, true, false);
                case 3 -> ejemplosEnteros(sc, true, true);
                case 4 -> System.out.println("Regresando al menu principal...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 4);
    }

    /** Evita repetición de try-catch */
    private static int leerOpcion(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void manejarContactos(Scanner sc, boolean doble, boolean circular) {
        LinkedList<Contactos> miLista;

        if (!doble && !circular) {
            if (listaSimple == null) {
                listaSimple = new LinkedList<>(false, false);
                DataTypeExamples.testComplexObjects(listaSimple);
            }
            miLista = listaSimple;
        } else if (doble && !circular) {
            if (listaDoble == null) {
                listaDoble = new LinkedList<>(true, false);
                DataTypeExamples.testComplexObjects(listaDoble);
            }
            miLista = listaDoble;
        } else {
            if (listaCircular == null) {
                listaCircular = new LinkedList<>(true, true);
                DataTypeExamples.testComplexObjects(listaCircular);
            }
            miLista = listaCircular;
        }
        interactuarConLista(miLista, sc, Contactos.class);
    }

    private static void ejemplosEnteros(Scanner sc, boolean doble, boolean circular) {
        LinkedList<Integer> miLista = new LinkedList<>(doble, circular);
        DataTypeExamples.testIntegers(miLista);
        interactuarConLista(miLista, sc, Integer.class);
    }

    /** Interacciones genéricas con listas */
    private static <T> void interactuarConLista(LinkedList<T> lista, Scanner sc, Class<T> tipo) {
        int opcion;
        do {
            System.out.println("\n>>> Opciones disponibles <<<");
            System.out.println("1. Agregar al inicio");
            System.out.println("2. Agregar al final");
            System.out.println("3. Eliminar elemento");
            System.out.println("4. Buscar elemento");
            System.out.println("5. Mostrar lista");
            System.out.println("6. Salir de esta lista");
            System.out.print("Elige: ");
            opcion = leerOpcion(sc);

            switch (opcion) {
                case 1 -> lista.insertAtFirstPosition(solicitarDato(sc, tipo));
                case 2 -> lista.insertAtLastPosition(solicitarDato(sc, tipo));
                case 3 -> {
                    T dato = solicitarDatoParaEliminar(sc, tipo);
                    System.out.println(lista.remove(dato) ? "Eliminado con exito." : "No se encontro el elemento.");
                }
                case 4 -> {
                    T dato = solicitarDatoParaEliminar(sc, tipo);
                    int pos = lista.indexOf(dato);
                    System.out.println(pos != -1 ? "Encontrado en posicion " + pos : "No esta en la lista.");
                }
                case 5 -> lista.show();
                case 6 -> System.out.println("Volviendo al submenu...");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 6);
    }

    /** Solicitar datos con validaciones */
    private static <T> T solicitarDato(Scanner sc, Class<T> tipo) {
        if (tipo == Contactos.class) {
            String nombre, direccion, telefono;

            // Validar nombre solo letras y espacios
            do {
                System.out.print("Nombre: ");
                nombre = sc.nextLine().trim();
                if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                    System.out.println("El nombre solo puede contener letras y espacios.");
                    nombre = "";
                }
            } while (nombre.isEmpty());

            // Dirección (se acepta cualquier cosa, pero no vacía)
            do {
                System.out.print("Direccion: ");
                direccion = sc.nextLine().trim();
            } while (direccion.isEmpty());

            // Teléfono solo números
            do {
                System.out.print("Telefono: ");
                telefono = sc.nextLine().trim();
                if (!telefono.matches("\\d+")) {
                    System.out.println("El telefono debe contener solo numeros.");
                    telefono = "";
                }
            } while (telefono.isEmpty());

            return (T) new Contactos(nombre, direccion, telefono);
        } else {
            // Validar que realmente sea un número entero
            String input;
            Integer numero = null;
            do {
                System.out.print("Numero: ");
                input = sc.nextLine().trim();
                if (input.matches("-?\\d+")) { // acepta enteros negativos
                    numero = Integer.valueOf(input);
                } else {
                    System.out.println("Por favor ingresa solo numeros enteros.");
                }
            } while (numero == null);
            return (T) numero;
        }
    }

    /** Solicitar dato para eliminar o buscar */
    private static <T> T solicitarDatoParaEliminar(Scanner sc, Class<T> tipo) {
        if (tipo == Contactos.class) {
            String nombre;
            do {
                System.out.print("Nombre del contacto a buscar/eliminar: ");
                nombre = sc.nextLine().trim();
                if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                    System.out.println("El nombre solo puede contener letras y espacios.");
                    nombre = "";
                }
            } while (nombre.isEmpty());
            return (T) new Contactos(nombre, "", "");
        } else {
            String input;
            Integer numero = null;
            do {
                System.out.print("Numero: ");
                input = sc.nextLine().trim();
                if (input.matches("-?\\d+")) {
                    numero = Integer.valueOf(input);
                } else {
                    System.out.println("Por favor ingresa solo numeros enteros.");
                }
            } while (numero == null);
            return (T) numero;
        }
    }
}
