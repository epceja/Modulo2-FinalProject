package chatProject;

import java.util.Scanner;
import java.util.logging.Logger;

public class MainUsers {

    private static final Logger LOGGER = Logger.getLogger(MainUsers.class.getName());
    private static final String CLEAR_CHARACTER = "\033[H\033[2J";
    private static final Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

        String leidoDeTeclado = "";
        boolean continuarMenuPrincipal = true;
        int id = 0;
        String username = "";
        String password = "";
        String email = "";

        while (continuarMenuPrincipal) {
            System.out.print(CLEAR_CHARACTER);
            System.out.println("----------------¡ BIENVENIDO!----------------");
            System.out.println("--ADMINISTRAR USUARIOS DE UNA BASE DE DATOS--");
            System.out.println("---------------------------------------------");
            System.out.println("1. Crear un Usuario Nuevo");
            System.out.println("2. Consultar un Usuario por ID");
            System.out.println("3. Consultar un Usuario por Nombre de Usuario");
            System.out.println("4. Consultar un Usuario por email");
            System.out.println("5. Consultar todos los Usuarios");
            System.out.println("6. Salir");
            System.out.println("---------------------------------------------");
            System.out.print("Elija la Opción Deseada y presione <Enter> --> ");
            leidoDeTeclado = teclado.nextLine();

            switch (leidoDeTeclado) {
                case "1":
                    System.out.print(CLEAR_CHARACTER);
                    System.out.println("***** Creando un Nuevo Usuario *****");
                    System.out.print("Ingrese el Nombre de Usuario (username) ");
                    leidoDeTeclado = teclado.nextLine();
                    username = leidoDeTeclado;
                    System.out.print("Ingrese la Constraseña (password) ");
                    leidoDeTeclado = teclado.nextLine();
                    password = leidoDeTeclado;
                    System.out.print("Ingrese el Correo Electrónico (email) ");
                    leidoDeTeclado = teclado.nextLine();
                    email = leidoDeTeclado;
                    System.out.println("\n" + username + " " + password + " " + email);
                    UserService.createUser(username, password, email);
                    break;
                case "2":
                    System.out.print(CLEAR_CHARACTER);
                    System.out.println("***** Consultar Usuario por ID *****");
                    System.out.print("Ingrese el ID del Usuario (id) ");
                    leidoDeTeclado = teclado.nextLine();
                    id = Integer.valueOf(leidoDeTeclado);
                    System.out.println("El id es: " + id);
                    UserService.readUserById(id);
                    break;
                case "3":
                    System.out.print(CLEAR_CHARACTER);
                    System.out.println("***** Consultar Usuario por Nombre de Usuario *****");
                    System.out.print("Ingrese el Nombre de Usuario (username) ");
                    leidoDeTeclado = teclado.nextLine();
                    username = leidoDeTeclado;
                    System.out.println("El username es: " + username);
                    UserService.readUserByUsername(username);
                    break;
                case "4":
                    System.out.print(CLEAR_CHARACTER);
                    System.out.println("***** Consultar Usuario por Correo Electrónico *****");
                    System.out.print("Ingrese el Correo Electrónico (email) ");
                    leidoDeTeclado = teclado.nextLine();
                    email = leidoDeTeclado;
                    System.out.println("El email es: " + email);
                    UserService.readUserByEmail(email);
                    break;
                case "5":
                    System.out.println("***** Consultar todos los usuarios *****");
                    UserService.readUsers();
                    break;
                case "6":
                    System.out.print(CLEAR_CHARACTER);
                    System.out.println("\n\n\nGracias Por Utilizar Este Programa. ¡Hasta Luego!");
                    continuarMenuPrincipal = false;
                    break;
                default:
                    System.out.println("\n\n¡¡¡ Error debe elegir de 1 a 6 !!!\n");
                    break;
            }


        }




    }

}
