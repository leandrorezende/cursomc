package com.leandrorezende.cursomc;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leandrorezende.cursomc.domain.Categoria;
import com.leandrorezende.cursomc.domain.Cidade;
import com.leandrorezende.cursomc.domain.Cliente;
import com.leandrorezende.cursomc.domain.Endereco;
import com.leandrorezende.cursomc.domain.Estado;
import com.leandrorezende.cursomc.domain.Produto;
import com.leandrorezende.cursomc.domain.enums.TipoCliente;
import com.leandrorezende.cursomc.repositories.CategoriaRepository;
import com.leandrorezende.cursomc.repositories.CidadeRepository;
import com.leandrorezende.cursomc.repositories.ClienteRepository;
import com.leandrorezende.cursomc.repositories.EnderecoRepository;
import com.leandrorezende.cursomc.repositories.EstadoRepository;
import com.leandrorezende.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(asList(p1, p2, p3));
		cat2.getProdutos().addAll(asList(p2));
		
		p1.getCategorias().addAll(asList(cat1));
		p2.getCategorias().addAll(asList(cat1, cat2));
		p3.getCategorias().addAll(asList(cat1));
		
		categoriaRepository.saveAll(asList(cat1, cat2));
		produtoRepository.saveAll(asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(asList(c1));
		est1.getCidades().addAll(asList(c2, c3));
		
		estadoRepository.saveAll(asList(est1, est2));
		cidadeRepository.saveAll(asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "363788912377", TipoCliente.PESSSOAFISICA);
		cli1.getTelefones().addAll(asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "37777012", cli1, c2);
		
		cli1.getEnderecos().addAll(asList(e1, e2));
		
		clienteRepository.saveAll(asList(cli1));
		enderecoRepository.saveAll(asList(e1, e2));		
	}
}
