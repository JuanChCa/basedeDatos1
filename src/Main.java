

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        Scanner sc = new Scanner(System.in);
        registro reg = new registro();
        int opcion;

        do {
            System.out.println("Menu");
            System.out.println("1. Agregar Estudiante");
            System.out.println("2. Eliminar Estudiante");
            System.out.println("3. Listar Estudiantes");
            System.out.println("4. Guardar en un fichero de texto");
            System.out.println("5. Cargar desde un fichero de texto");
            System.out.println("6. Guardar en fichero binario");
            System.out.println("7. Cargar desde un fichero binario");
            System.out.println("8. Guardar en fichero XML");
            System.out.println("9. Cargar desde un fichero XML");
            System.out.println("10. Salir");

            System.out.print("Introduce la opción deseada: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del estudiante: ");
                    String nombre = sc.nextLine();
                    System.out.print("Edad del estudiante: ");
                    int edad = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Matricula del estudiante: ");
                    String matricula = sc.nextLine();
                    System.out.print("Nota media del estudiante: ");
                    double notaMedia = sc.nextDouble();
                    System.out.print("¿Está matriculado? Si=true, No=false: ");
                    boolean matriculado = sc.nextBoolean();

                    reg.agregarEstudiantes(nombre, edad, matricula, notaMedia, matriculado);
                    break;
                case 2:
                    System.out.print("Matricula del estudiante para eliminar: ");
                    String matriculaEliminar = sc.nextLine();
                    reg.EliminarEstudiantes(matriculaEliminar);
                    System.out.println("Estudiante eliminado");
                    break;
                case 3:
                    reg.mostrarEstudiantes();
                    break;
                case 4:
                    try {
                        reg.guardarTexto("listaEstudiantes.txt");
                        System.out.println("Estudiantes guardados en texto.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        reg.cargarTexto("listaEstudiantes.txt");
                        System.out.println("Estudiantes cargados desde texto.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        reg.guardarBinario("listaEstudiantes.dat");
                        System.out.println("Estudiantes guardados en binario.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        reg.cargarBinario("listaEstudiantes.dat");
                        System.out.println("Estudiantes cargados desde binario.");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    reg.guardarXML("listaEstudiantes.xml");
                    System.out.println("Estudiantes guardados en XML.");
                    break;
                case 9:
                    reg.cargarXML("listaEstudiantes.xml");
                    System.out.println("Estudiantes cargados desde XML.");
                    break;
                case 10:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 10);
        sc.close();
    }
}