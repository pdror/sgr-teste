package br.com.sgr.demo.repositories;

import br.com.sgr.demo.entities.Pessoas;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoasRepository {
    private List<Pessoas> pessoas = new ArrayList<>();

    /**
     * Método construtor que popula o repositório com dados fictícios
     */
    public PessoasRepository() {
        pessoas.addAll(List.of(
                new Pessoas(1, "PEDRO RAMOS", "RUA JOSE BATISTA 480", "PEDROHFRAMOS@OUTLOOK.COM", LocalDate.of(1997,9,11)),
                new Pessoas(2, "ISADORA MENDES", "RUA ARLINDO BAPTISTA 426", LocalDate.of(1971,2,13)),
                new Pessoas(3, "VERA BERNARDES", "RUA JOSÉ SARDINHA 242", "VERABERNADES@PM.ME", LocalDate.of(1996,11,21)),
                new Pessoas(4, "PAULO VINICIUS", "RUA A 154", "PAULOVINICIUS@GMAIL.COM", LocalDate.of(1982,12, 7)),
                new Pessoas(5, "EMILY CAMPOS", "RUA MARROCOS 819", LocalDate.of(1954,7, 3))
        ));
    }

    /**
     * @return Lista de pessoas
     */
    @GetMapping
    public List<Pessoas> getPessoas() {
        return pessoas;
    }

    @GetMapping("/{id_pessoa}")
    public Pessoas getById(@PathVariable Integer id_pessoa) {
        for(Pessoas p : pessoas)
            if(p.getId_pessoa() == id_pessoa)
                return p;
        return null;
    }
}
