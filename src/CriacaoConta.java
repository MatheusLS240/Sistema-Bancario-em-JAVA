import java.util.*;

public class CriacaoConta {
    public static void criarConta(Scanner sc, Map<Integer, Conta> listaDeContas) {
        Conta conta = null;
        System.out.println("\n( -- Cadastro de Nova Conta -- )");
        try {
            System.out.print("Nome: ");
            String nome = CampoUsuario.toCapitalize(sc);
            System.out.print("Gênero (Masculino/Feminino): ");
            String genero = CampoUsuario.verificarGenero(sc);
            System.out.print("CPF: ");
            String cpf = CampoUsuario.verificarCampo(sc);
            System.out.print("Email: ");
            String email = CampoUsuario.verificarCampo(sc);
            System.out.print("Telefone: ");
            String telefoneStr = CampoUsuario.verificarCampo(sc);
            long telefone = Long.parseLong(telefoneStr);
            System.out.print("Senha: ");
            String senha = CampoUsuario.verificarCampo(sc);
            System.out.print("Tipo de conta (Corrente/Poupanca): ");
            String opcaoConta = CampoUsuario.verificarCampo(sc);

            TipoContas tipo = TipoContas.converterContas(opcaoConta);

            if (tipo == null) {
                System.out.println("\n( -- Cadastro de Nova Conta -- )");
                System.out.println("Tipo de conta inválido.");
            } else {
                System.out.println("\n( -- Cadastro de Nova Conta -- )");
                System.out.println("Confirme seus dados:");
                System.out.println("Nome: " + nome);
                System.out.println("Gênero: " + genero);
                System.out.println("CPF: " + cpf);
                System.out.println("Email: " + email);
                System.out.println("Telefone: " + telefone);
                System.out.println("Tipo de Conta: " + (tipo == TipoContas.CORRENTE ? "Corrente" : "Poupança"));
                System.out.print("Confirma as informações? (sim/não): ");
                String confirmacao = CampoUsuario.verificarCampo(sc).toLowerCase();

                if (confirmacao.equals("sim")) {
                    conta = criarContaPorTipo(tipo, nome, genero, cpf, email, telefone, senha);
                    listaDeContas.put(conta.getNumConta(), conta);
                    System.out.println("\n( -- Cadastro de Nova Conta -- )");
                    System.out.println("Conta criada com sucesso. Número da conta: " + conta.getNumConta());
                } else {
                    System.out.println("\n( -- Cadastro de Nova Conta -- )");
                    System.out.println("Operação cancelada pelo usuário.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("\n( -- Cadastro de Nova Conta -- )");
            System.out.println("Telefone inválido.");
        } catch (Exception e) {
            System.out.println("\n( -- Cadastro de Nova Conta -- )");
            System.out.println("Erro ao criar conta. Tente novamente.");
        }
    }

    private static Conta criarContaPorTipo(TipoContas tipo, String nome, String genero, String cpf, String email, long telefone, String senha) {
        return switch (tipo) {
            case CORRENTE -> new ContaCorrente(nome, genero, cpf, email, telefone, senha);
            case POUPANCA -> new ContaPoupanca(nome, genero, cpf, email, telefone, senha);
        };
    }
}
