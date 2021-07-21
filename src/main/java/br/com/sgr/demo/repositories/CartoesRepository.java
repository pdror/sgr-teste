package br.com.sgr.demo.repositories;

import br.com.sgr.demo.entities.Cartoes;
import br.com.sgr.demo.entities.Contas;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cartoes")
public class CartoesRepository {
    private List<Cartoes> cartoes = new ArrayList<>();

    /**
     * Método construtor que popula o repositório com dados fictícios
     */
    public CartoesRepository() {
        cartoes.addAll(List.of(
                new Cartoes(1,1,"5139731086120063"),
                new Cartoes(2,2,"5386250791275369"),
                new Cartoes(3,3,"5542734742932088"),
                new Cartoes(4,4,"5588901272564756"),
                new Cartoes(5,5,"5266264785444936")
        ));
    }

    /**
     *  @return Lista de cartões
     */
    @GetMapping
    public List<Cartoes> getCartoes() {
        return cartoes;
    }

    @GetMapping("/{id_cartao}")
    public Cartoes getById(@PathVariable Integer id_cartao) {
        for(Cartoes c : cartoes)
            if(c.getId_cartao() == id_cartao)
                return c;
        return null;
    }
}
