package br.com.sgr.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Cartoes {
    private Integer id_cartao;
    private Integer id_conta;
    private String numero;

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("id_cartao = ").append(id_cartao)
                .append("\t id_conta = ").append(id_conta)
                .append("\t numero = ").append(numero).append("\n");

        return String.valueOf(output);
    }
}
