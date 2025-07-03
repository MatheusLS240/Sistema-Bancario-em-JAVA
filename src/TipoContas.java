public enum TipoContas {
    CORRENTE,
    POUPANCA;

    public static TipoContas converterContas(String opcao) {
        if (opcao != null) {
            try {
                return TipoContas.valueOf(opcao.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Opção inválida: " + opcao);
            }
        }
        System.out.println("Campo vazio ou caracteres ilegais");
        return null;
    }
}

