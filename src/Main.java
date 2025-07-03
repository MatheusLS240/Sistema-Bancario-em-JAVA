import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, Conta> listaDeContas = new HashMap<>();

        while (true) {
            System.out.println("\n( -- Sistema Bancário -- )");
            System.out.println("1 - Criar Conta");
            System.out.println("2 - Acessar Conta");
            System.out.println("0 - Encerrar");
            System.out.print("Selecione uma opção: ");
            String opcao = sc.nextLine();

            switch (opcao) {
                case "1" -> CriacaoConta.criarConta(sc, listaDeContas);
                case "2" -> LoginConta.loginConta(sc, listaDeContas);
                case "0" -> {
                    System.out.println("\n( -- Sistema Bancário -- )");
                    System.out.println("Sessão encerrada. Obrigado por utilizar o sistema.");
                    sc.close();
                    return;
                }
                default -> {
                    System.out.println("\n( -- Sistema Bancário -- )");
                    System.out.println("Opção inválida.");
                }
            }
        }
    }
}
