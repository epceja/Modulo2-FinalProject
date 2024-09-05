package chatProject;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainMessages {

    private static final Logger LOGGER = Logger.getLogger(MainUsers.class.getName());
    private static final String CLEAR_CHARACTER = "\033[H\033[2J";
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        String leidoDeTeclado = "";
        boolean continuarMenuPrincipal = true;
        int id = 0;
        String content = "";
        Timestamp timestamp = Timestamp.valueOf(LocalDate.now().atStartOfDay());

        while (continuarMenuPrincipal) {
            System.out.print(CLEAR_CHARACTER);
            System.out.println("----------------¡ BIENVENIDO!----------------");
            System.out.println("--ADMINISTRAR MENSAJES DE UNA BASE DE DATOS--");
            System.out.println("---------------------------------------------");
            System.out.println("1. Crear un Mensaje Nuevo");
            System.out.println("2. Consultar un Mensaje por ID");
            System.out.println("3. Salir");
            System.out.println("---------------------------------------------");
            System.out.print("Elija la Opción Deseada y presione <Enter> --> ");
            leidoDeTeclado = teclado.nextLine();

            switch (leidoDeTeclado) {
                case "1":
                    System.out.print(CLEAR_CHARACTER);
                    System.out.println("***** Creando un Nuevo Mensaje *****");
                    System.out.print("Ingrese el Contenido del Mensaje (content) ");
                    leidoDeTeclado = teclado.nextLine();
                    content = leidoDeTeclado;
                    System.out.println("\n Se ha creado el mensaje: " + content);
                    MessageService.createMessage(content);
                    break;
                case "2":
                    System.out.print(CLEAR_CHARACTER);
                    System.out.println("***** Consultar Mensaje por ID *****");
                    System.out.print("Ingrese el ID del Mensaje (id) ");
                    leidoDeTeclado = teclado.nextLine();
                    id = Integer.valueOf(leidoDeTeclado);
                    System.out.println("El id es: " + id);
                    MessageService.readMessageById(id);
                    break;
                case "3":
                    System.out.println("***** Consultar los últimos 10 mensajes *****");
                    MessageService.readLastMessages(10);
                    break;
                case "4":
                    System.out.print(CLEAR_CHARACTER);
                    System.out.println("\n\n\nGracias Por Utilizar Este Programa. ¡Hasta Luego!");
                    continuarMenuPrincipal = false;
                    break;
                default:
                    System.out.println("\n\n¡¡¡ Error debe elegir de 1 a 4 !!!\n");
                    break;
            }

            }

    }
}
