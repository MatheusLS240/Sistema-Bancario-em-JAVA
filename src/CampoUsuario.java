import java.util.*;

public enum CampoUsuario {
    NOME,
    GENERO,
    CPF,
    EMAIL,
    TELEFONE,
    SENHA,
    TODOS;

    public static CampoUsuario converterCampo(String opcao) {
        if (opcao != null) {
            try {
                return CampoUsuario.valueOf(opcao.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Opção inválida: " + opcao);
            }
        } else {
            System.out.println("Campo vazio ou caracteres ilegais");
        }
        return null;
    }

    public static String verificarCampo(Scanner sc) throws Exception {
        String valor = sc.nextLine();
        int i = 1;
        while (valor.isEmpty()) {
            if (i <= 3) {
                System.err.printf("(%d / 3) Campo vazio! Tente novamente: ", i);
                valor = sc.nextLine();
            } else {
                throw new Exception("Limite de tentativas excedido");
            }
            i++;
        }
        return valor;
    }

    public static String verificarGenero(Scanner sc) throws Exception {
        String genero;
        int tentativas = 0;
        do {
            tentativas++;   

            genero = sc.nextLine().trim().toLowerCase();
            if(genero.isEmpty()) {
                System.out.printf("(%d / 3) Campo vazio! Tente novamente: ", tentativas);
            } else if (!genero.equals("masculino") && !genero.equals("feminino")) {
                System.err.printf("(%d / 3) Gênero inválido! Digite 'Masculino' ou 'Feminino': ", tentativas);
            } else {
                return toCapitalize(genero);
            }
        } while (tentativas < 4);
        throw new Exception("Limite de tentativas excedido");
    }

    public static String toCapitalize(Scanner sc) throws Exception {
        String campo = verificarCampo(sc);
        return campo.substring(0, 1).toUpperCase() + campo.substring(1).toLowerCase();
    }

    public static String toCapitalize(String item) throws Exception {
        return item.substring(0, 1).toUpperCase() + item.substring(1).toLowerCase();
    }
}
