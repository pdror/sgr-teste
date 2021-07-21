package br.com.sgr.demo.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.Normalizer;
import java.util.regex.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Registry {
    private Integer id_pessoa;
    private String nome;
    private String endereco;
    private String email;
    private Integer id_conta;
    private Integer id_cartao;
    private String numero;

    /**
     * Remove a acentuação dos registros que serão escritos no arquivo de texto
     * @param str
     * @return String
     */
    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    /**
     * Define o padrão de escrita no arquivo
     * @return
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(id_pessoa).append(";")
                .append(nome.replaceAll(" ", "&")).append(";")
                .append(endereco).append(";")
                .append(email).append(";")
                .append(id_conta).append(";")
                .append(id_cartao).append(";")
                .append(numero).append(";");
        return deAccent(String.valueOf(output));
    }
}
