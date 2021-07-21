package br.com.sgr.demo;

import br.com.sgr.demo.entities.Cartoes;
import br.com.sgr.demo.entities.Contas;
import br.com.sgr.demo.entities.Pessoas;
import br.com.sgr.demo.helpers.FilesHelper;
import br.com.sgr.demo.helpers.Registry;
import br.com.sgr.demo.repositories.CartoesRepository;
import br.com.sgr.demo.repositories.ContasRepository;
import br.com.sgr.demo.repositories.PessoasRepository;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

	private PessoasRepository pessoasRepository;
	private ContasRepository contasRepository;
	private CartoesRepository cartoesRepository;

	@Autowired
	public DemoApplication(PessoasRepository pessoasRepository, ContasRepository contasRepository, CartoesRepository cartoesRepository) {
		this.pessoasRepository = pessoasRepository;
		this.contasRepository = contasRepository;
		this.cartoesRepository = cartoesRepository;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("1. Iniciando Spring Boot Application");
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * Método Spring ApplicationRunner acionado após a execução imediata do programa
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("\n 2. Iniciando ApplicationRunner");

		System.out.println("\n Listando todas as ocorrências de PESSOAS: ");
		List<Pessoas> pessoasList = pessoasRepository.getPessoas();
		System.out.println(pessoasList.toString());

		System.out.println("\n Buscando PESSOA com id_pessoa = 3 ");
		System.out.println(pessoasRepository.getById(3));

		System.out.println("\n Listando todas as ocorrências de CONTAS");
		List<Contas> contasList = contasRepository.getContas();
		System.out.println(contasList.toString());

		System.out.println("\n Listando todas as ocorrências de CARTOES");
		List<Cartoes> cartoesList = cartoesRepository.getCartoes();
		System.out.println(cartoesList.toString());

		List<Registry> registryList = createRegistry(pessoasList, contasList, cartoesList);
		writeOnFile(registryList);
	}

	/**
	 * Cria um registro para facilitar a escrita no arquivo de texto
	 * @param pessoasList
	 * @param contasList
	 * @param cartoesList
	 * @return Lista de registros
	 * @throws IOException
	 */
	public static List<Registry> createRegistry(List<Pessoas> pessoasList, List<Contas> contasList, List<Cartoes> cartoesList) throws IOException {
		List<Registry> registryList = new ArrayList<>();

		pessoasList.forEach(pessoas -> {
			Registry newRegistry = new Registry();

			newRegistry.setId_pessoa(pessoas.getId_pessoa());
			newRegistry.setNome(pessoas.getNome());
			newRegistry.setEndereco(pessoas.getEndereco());
			newRegistry.setEmail(pessoas.getEmail());

			for(Contas contas : contasList) {
				if(contas.getId_pessoa().equals(pessoas.getId_pessoa())) {
					newRegistry.setId_conta(contas.getId_conta());
				}
			}

			for(Cartoes cartoes : cartoesList) {
				if(cartoes.getId_conta().equals(newRegistry.getId_conta())) {
					newRegistry.setId_cartao(cartoes.getId_cartao());
					newRegistry.setNumero(cartoes.getNumero());
				}
			}

			registryList.add(newRegistry);
		});

		return registryList;
	}

	/**
	 * Chama métodos de manipulação de arquivos de texto
	 * @param registryList
	 * @throws IOException
	 */
	public static void writeOnFile(List<Registry> registryList) throws IOException {
		FilesHelper filesHelper = new FilesHelper();
		File dir = filesHelper.createFolder();
		File newFile = filesHelper.createFile(dir, registryList);

		if(newFile.exists()) {
			System.out.println("Novo arquivo criado");
		}
	}
}
