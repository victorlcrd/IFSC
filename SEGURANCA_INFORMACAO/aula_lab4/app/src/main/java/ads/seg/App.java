package ads.seg;

import java.io.Console;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        UserRepository repository = new InMemoryUserRepository();
        UserService userService = new UserService(repository);

        System.out.println("--- Sistema de Autenticação ---");
        System.out.println("Implementação de " + repository.getClass().getSimpleName());

        Console console = System.console();
        if (console == null) {

            System.err.println("Console não disponível. Execute fora da IDE.");
            System.err.println("Use: ./gradlew installDist"); //
            System.err.println("E depois: ./app/build/install/app/bin/app"); //
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Registrar (RF1)");
            System.out.println("2. Autenticar (RF3)");
            System.out.println("3. Atualizar Senha (RF2)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int option = -1;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            try {
                switch (option) {
                    case 1:
                        handleRegister(console, userService);
                        break;
                    case 2:
                        handleAuthenticate(console, userService);
                        break;
                    case 3:
                        handleUpdatePassword(console, userService);
                        break;
                    case 0:
                        running = false;
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.err.println("ERRO: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void handleRegister(Console console, UserService userService) throws Exception {
        System.out.println("\n[ 1. Registrar Usuário ]");
        String login = console.readLine("Login: ");
        char[] password = console.readPassword("Senha: ");

        userService.register(login, password);
        System.out.println("Usuário '" + login + "' registrado com sucesso!");
    }

    private static void handleAuthenticate(Console console, UserService userService) throws Exception {
        System.out.println("\n[ 2. Autenticar Usuário ]");
        String login = console.readLine("Login: ");
        char[] password = console.readPassword("Senha: ");

        userService.authenticate(login, password);
        System.out.println("Usuário '" + login + "' autenticado com sucesso!");
    }

    private static void handleUpdatePassword(Console console, UserService userService) throws Exception {
        System.out.println("\n[ 3. Atualizar Senha ]");
        String login = console.readLine("Login: ");
        char[] oldPassword = console.readPassword("Senha Antiga: ");
        char[] newPassword = console.readPassword("Senha Nova: ");

        userService.updatePassword(login, oldPassword, newPassword);
        System.out.println("Senha de '" + login + "' atualizada com sucesso!");
    }
}