package br.com.sgr.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoas {
    private Integer id_pessoa;
    private String nome;
    private String endereco;
    private String email;

    //@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate data_nascimento;

    /**
     * Método construtor sem o campo 'email'
     * @param id_pessoa
     * @param nome
     * @param endereco
     * @param data_nascimento
     */
    public Pessoas(Integer id_pessoa, String nome, String endereco, LocalDate data_nascimento) {
        this.id_pessoa = id_pessoa;
        this.nome = nome;
        this.endereco = endereco;
        this.data_nascimento = data_nascimento;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("id_pessoa = ").append(id_pessoa)
                .append("\t nome = ").append(nome.replaceAll(" ", "&"))
                .append("\t endereco = ").append(endereco)
                .append("\t email = ").append(email)
                .append("\t data = ").append(data_nascimento).append("\n");
        return String.valueOf(output);
    }
}
