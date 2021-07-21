package br.com.sgr.demo.repositories;

import br.com.sgr.demo.entities.Contas;
import br.com.sgr.demo.entities.Pessoas;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContasRepository {
    private List<Contas> contas = new ArrayList<>();

    /**
    * Método construtor que popula o repositório com dados fictícios
    */
    public ContasRepository() {
        for (int i = 1; i <= 5; i++) {
            contas.add(new Contas(i,i));
        }
    }

    /**
    *  @return Lista de contas
    */
    @GetMapping
    public List<Contas> getContas() { return contas; }

    @GetMapping("/{id_conta}")
    public Contas getById(@PathVariable Integer id_conta) {
        for(Contas c : contas)
            if(c.getId_conta() == id_conta)
                return c;
        return null;
    }
}
