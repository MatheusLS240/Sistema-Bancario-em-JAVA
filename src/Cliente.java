public class Cliente {
    private String nome;
    private String genero;
    private String cpf;
    private String email;
    private long telefone;

    public Cliente(String nome, String genero, String cpf, String email, long telefone) {
        this.nome = nome;
        this.genero = genero;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public long getTelefone() { return telefone; }
    public void setTelefone(long telefone) { this.telefone = telefone; }
}
