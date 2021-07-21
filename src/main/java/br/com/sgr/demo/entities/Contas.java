package br.com.sgr.demo.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contas {
    private Integer id_conta;
    private Integer id_pessoa;

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("id_conta = ").append(id_conta)
                .append("\t id_pessoa = ").append(id_pessoa).append("\n");

        return String.valueOf(output);
    }
}
