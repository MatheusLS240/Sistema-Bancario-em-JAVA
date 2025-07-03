import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ContaPoupanca extends Conta {
    private double taxaRendimento = 0.005;
    private int carenciaDias = 10;
    private double valorTotalRenda = 0.0;
    private LocalDate dataAplicacao = null;
    private boolean valorAplicado = false;

    public ContaPoupanca(String nome, String genero, String cpf, String email, long telefone, String senha) {
        super(nome, genero, cpf, email, telefone);
        setSenha(senha);
        setNumConta((int) (Math.random() * 10000));
    }

    @Override
    public void sacar(Scanner sc) {
        aplicarRendimentosMensais();
        System.out.println("\n( -- Saque Conta " + getNumConta() + " -- )");
        System.out.print("Informe o valor para saque: ");
        double valor = sc.nextDouble();
        sc.nextLine(); 

        if (valorAplicado && valor == valorTotalRenda) {
            if (verificarCarenciaDias()) {
                setSaldo(getSaldo() + valorTotalRenda);
                System.out.println("\n( -- Saque Conta " + getNumConta() + " -- )");
                System.out.println("Resgate de rendimentos efetuado com sucesso.");
                valorAplicado = false;
                valorTotalRenda = 0.0;
            } else {
                long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), dataAplicacao.plusDays(carenciaDias));
                System.out.println("\n( -- Saque Conta " + getNumConta() + " -- )");
                System.out.println("Resgate indisponível. Aguarde " + diasRestantes + " dia(s) para sacar os rendimentos.");
            }
        } else if (valor <= getSaldo() && valor >= 1) {
            setSaldo(getSaldo() - valor);
            System.out.println("\n( -- Saque Conta " + getNumConta() + " -- )");
            System.out.println("Saque realizado com sucesso.");
        } else {
            System.out.println("\n( -- Saque Conta " + getNumConta() + " -- )");
            System.out.println("Valor inválido ou saldo insuficiente.");
        }
    }

    public void aplicarRendimentosMensais() {
        LocalDate hoje = LocalDate.now();
        if (getUltimaAtualizacao().plusMonths(1).isBefore(hoje) || getUltimaAtualizacao().plusMonths(1).equals(hoje)) {
            valorTotalRenda = getSaldo() * taxaRendimento;
            setSaldo(getSaldo() + valorTotalRenda);
            setUltimaAtualizacao(hoje);
            this.valorAplicado = true;
            if (this.dataAplicacao == null) {
                this.dataAplicacao = hoje;
            }
            System.out.println("\n( -- Aplicar Rendimentos Conta " + getNumConta() + " -- )");
            System.out.println("Rendimentos aplicados à conta poupança.");
        }
    }

    private boolean verificarCarenciaDias() {
        LocalDate hoje = LocalDate.now();
        if (dataAplicacao != null) {
            return this.dataAplicacao.plusDays(carenciaDias).isBefore(hoje) || this.dataAplicacao.plusDays(carenciaDias).equals(hoje);
        }
        return false;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    public int getCarenciaDias() {
        return carenciaDias;
    }

    public void setCarenciaDias(int carenciaDias) {
        this.carenciaDias = carenciaDias;
    }

    public double getValorTotalRenda() {
        return valorTotalRenda;
    }

    public void setValorTotalRenda(double valorTotalRenda) {
        this.valorTotalRenda = valorTotalRenda;
    }

    public LocalDate getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(LocalDate dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }

    public boolean isValorAplicado() {
        return valorAplicado;
    }

    public void setValorAplicado(boolean valorAplicado) {
        this.valorAplicado = valorAplicado;
    }
}
