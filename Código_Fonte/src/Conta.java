import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public abstract class Conta extends Cliente {
    private int numConta;
    private double saldo = 0.0;
    private String senha;
    private LocalDate ultimaAtualizacao = LocalDate.now();

    public Conta(String nome, String genero, String cpf, String email, long telefone) {
        super(nome, genero, cpf, email, telefone);
    }

    public void depositar(Scanner sc) {
        System.out.println("\n( -- Depósito Conta " + getNumConta() + " -- )");
        System.out.print("Informe o valor para depósito: ");
        double valor = sc.nextDouble();
        sc.nextLine(); 
        
        if (valor >= 1) {
            this.saldo += valor;
            System.out.println("\n( -- Depósito Conta " + getNumConta() + " -- )");
            System.out.println("Depósito realizado com sucesso.");
        } else {
            System.out.println("\n( -- Depósito Conta " + getNumConta() + " -- )");
            System.out.println("Valor inválido para depósito.");
        }
    }

    public abstract void sacar(Scanner sc);

    public void transferir(Scanner sc, Map<Integer, Conta> listaDeContas) {
        System.out.println("\n( -- Transferência Conta " + getNumConta() + " -- )");
        System.out.print("Número da conta de destino: ");
        int numero = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Valor da transferência: ");
        double valor = sc.nextDouble();
        sc.nextLine(); 

        Conta destino = listaDeContas.get(numero);

        if(valor <= 0) {
            System.out.println("\n( -- Transferência Conta " + getNumConta() + " -- )");
            System.out.println("Valor inválido para transferência.");
            return;
        }

        if (destino != null && getSaldo() >= valor) {
            this.saldo -= valor;
            setSaldo(saldo);
            System.out.println("\n( -- Transferência Conta " + getNumConta() + " -- )");
            System.out.println("Transferência realizada com sucesso.");
            return;
        } 

        if(destino == null) {
            System.out.println("\n( -- Transferência Conta " + getNumConta() + " -- )");
            System.out.println("Conta de destino inválida.");
        }
    }

    public void exibirExtrato() {
        System.out.println("\n( -- Extrato Conta " + getNumConta() + " -- )");
        System.out.printf("Saldo atual: R$ %.2f\n", getSaldo());
        System.out.println("Última atualização: " + getUltimaAtualizacao());
    }

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDate ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
}
