import java.time.LocalDate;
import java.util.Scanner;

public class ContaCorrente extends Conta {
    private double taxaSaque = 2.5;
    private double taxaManutencao = 12.0;

    public ContaCorrente(String nome, String genero, String cpf, String email, long telefone, String senha) {
        super(nome, genero, cpf, email, telefone);
        setSenha(senha);
        setNumConta((int) (Math.random() * 10000));
    }

    @Override
    public void sacar(Scanner sc) {
        verificarEncargosMensais();
        System.out.println("\n( -- Saque Conta " + getNumConta() + " -- )");
        System.out.print("Informe o valor para saque: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        if (valor + taxaSaque <= getSaldo() && valor >= 1) {
            setSaldo(getSaldo() - valor - taxaSaque);
            System.out.println("\n( -- Saque Conta " + getNumConta() + " -- )");
            System.out.println("Saque realizado com sucesso.");
            System.out.printf("Taxa de saque: R$ %.2f\n", taxaSaque);
            System.out.printf("Saldo atual: R$ %.2f\n", getSaldo());
        } else {
            System.out.println("\n( -- Saque Conta " + getNumConta() + " -- )");
            System.out.println("Valor inválido ou saldo insuficiente.");
        }
    }

    public void verificarEncargosMensais() {
        LocalDate hoje = LocalDate.now();
        if (getUltimaAtualizacao().plusMonths(1).isBefore(hoje) || getUltimaAtualizacao().plusMonths(1).equals(hoje)) {
            setSaldo(getSaldo() - taxaManutencao);
            setUltimaAtualizacao(hoje);
            System.out.println("\n( -- Encargos Conta " + getNumConta() + " -- )");
            System.out.println("Taxa de manutenção mensal debitada da conta corrente.");
        }
    }

    public double getTaxaSaque() {
        return taxaSaque;
    }

    public void setTaxaSaque(double taxaSaque) {
        this.taxaSaque = taxaSaque;
    }

    public double getTaxaManutencao() {
        return taxaManutencao;
    }

    public void setTaxaManutencao(double taxaManutencao) {
        this.taxaManutencao = taxaManutencao;
    }

    
}
