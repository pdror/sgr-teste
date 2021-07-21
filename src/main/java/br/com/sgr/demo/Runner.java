package br.com.sgr.demo;

import br.com.sgr.demo.repositories.CartoesRepository;
import br.com.sgr.demo.repositories.ContasRepository;
import br.com.sgr.demo.repositories.PessoasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class Runner implements ApplicationRunner {

    private PessoasRepository pessoasRepository;
    private ContasRepository contasRepository;
    private CartoesRepository cartoesRepository;

    @Autowired
    public Runner(PessoasRepository pessoasRepository, ContasRepository contasRepository, CartoesRepository cartoesRepository) {
        this.pessoasRepository = pessoasRepository;
        this.contasRepository = contasRepository;
        this.cartoesRepository = cartoesRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("\n 2. Iniciando ApplicationRunner");

        System.out.println("\n Listando todas as ocorrências de PESSOAS: ");
        System.out.println(pessoasRepository.getPessoas());

        System.out.println("\n Buscando PESSOA com id_pessoa = 3 ");
        System.out.println(pessoasRepository.getById(3));

        System.out.println("\n Listando todas as ocorrências de CONTAS");
        System.out.println(contasRepository.getContas());

        System.out.println("\n Listando todas as ocorrências de CARTOES");
        System.out.println(cartoesRepository.getCartoes());
    }
}
