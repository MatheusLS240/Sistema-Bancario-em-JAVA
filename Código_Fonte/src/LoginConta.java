import java.util.*;

public class LoginConta {
    public static void loginConta(Scanner sc, Map<Integer, Conta> listaDeContas) {
        if (listaDeContas.size() == 0) {
            System.out.println("\n( -- Login -- )");
            System.out.println("Nenhuma conta cadastrada no sistema.");
            System.out.println("Por favor, crie uma conta primeiro.");
            return;
        }

        System.out.println("\n( -- Login -- )");
        System.out.print("Número da conta: ");
        int num = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        validarConta(sc, listaDeContas, num, senha);
    }

    private static void validarConta(Scanner sc, Map<Integer, Conta> listaDeContas, int num, String senha) {
        boolean contaEncontrada = false;

        for (Map.Entry<Integer, Conta> entry : listaDeContas.entrySet()) {
            Conta conta = entry.getValue();
            if (entry.getKey().equals(num) && conta.getSenha().equals(senha)) {
                contaEncontrada = true;
                System.out.println("\n( -- Login -- )");
                if(entry.getValue().getGenero().equals("Masculino")) {
                    System.out.println("Bem-vindo, " + conta.getNome() + "!");
                } else {
                    System.out.println("Bem-vinda, " + conta.getNome() + "!");
                }
                menuPrincipal(sc, entry, listaDeContas);
                return;
            }
        }

        if (!contaEncontrada) {
            System.out.println("\n( -- Login -- )");
            System.out.println("Acesso negado. Número da conta ou senha incorretos.");
        }
    }

    private static void menuPrincipal(Scanner sc, Map.Entry<Integer, Conta> entry, Map<Integer, Conta> listaDeContas) {
        try {
            while (true) {
                Conta conta = entry.getValue();

                if (conta instanceof ContaPoupanca) {
                    ((ContaPoupanca) conta).aplicarRendimentosMensais();
                }
                if (conta instanceof ContaCorrente) {
                    ((ContaCorrente) conta).verificarEncargosMensais();
                }

                String tipoConta;
                if (conta instanceof ContaCorrente) {
                    tipoConta = "Corrente";
                } else if (conta instanceof ContaPoupanca) {
                    tipoConta = "Poupança";
                } else {
                    tipoConta = "Desconhecido";
                } 

                System.out.println("\n( -- Menu Conta " + conta.getNumConta() + " " +  tipoConta + " -- )");
                System.out.println("1 - Depósito");
                System.out.println("2 - Saque");
                System.out.println("3 - Transferência");
                System.out.println("4 - Extrato");
                System.out.println("0 - Sair");
                System.out.print("Selecione uma opção: ");
                String opcao = sc.nextLine();

                switch (opcao) {
                    case "1" -> conta.depositar(sc);
                    case "2" -> conta.sacar(sc);
                    case "3" -> conta.transferir(sc, listaDeContas);
                    case "4" -> conta.exibirExtrato();
                    case "0" -> {
                        System.out.println("\n( -- Menu Conta " + conta.getNumConta() + " -- )");
                        System.out.println("Sessão finalizada.");
                        return;
                    }
                    default -> {
                        System.out.println("\n( -- Menu Conta " + conta.getNumConta() + " -- )");
                        System.out.println("Opção inválida.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("\n( -- Menu Conta -- )");
            System.out.println("Erro ao processar a operação.");
            sc.nextLine();
        }
    }
}