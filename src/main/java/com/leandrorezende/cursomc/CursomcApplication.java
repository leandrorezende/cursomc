package com.leandrorezende.cursomc;

import static java.util.Arrays.asList;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leandrorezende.cursomc.domain.Categoria;
import com.leandrorezende.cursomc.domain.Cidade;
import com.leandrorezende.cursomc.domain.Cliente;
import com.leandrorezende.cursomc.domain.Endereco;
import com.leandrorezende.cursomc.domain.Estado;
import com.leandrorezende.cursomc.domain.ItemPedido;
import com.leandrorezende.cursomc.domain.Pagamento;
import com.leandrorezende.cursomc.domain.PagamentoComBoleto;
import com.leandrorezende.cursomc.domain.PagamentoComCartao;
import com.leandrorezende.cursomc.domain.Pedido;
import com.leandrorezende.cursomc.domain.Produto;
import com.leandrorezende.cursomc.domain.enums.EstadoPagamento;
import com.leandrorezende.cursomc.domain.enums.TipoCliente;
import com.leandrorezende.cursomc.repositories.CategoriaRepository;
import com.leandrorezende.cursomc.repositories.CidadeRepository;
import com.leandrorezende.cursomc.repositories.ClienteRepository;
import com.leandrorezende.cursomc.repositories.EnderecoRepository;
import com.leandrorezende.cursomc.repositories.EstadoRepository;
import com.leandrorezende.cursomc.repositories.ItemPedidoRepository;
import com.leandrorezende.cursomc.repositories.PagamentoRepository;
import com.leandrorezende.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;	
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(asList(ped1, ped2));
		
		pedidoRepository.saveAll(asList(ped1, ped2));
		pagamentoRepository.saveAll(asList(pagto1, pagto2));	
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(asList(ip1, ip2));
		ped2.getItens().addAll(asList(ip3));
		
		p1.getItens().addAll(asList(ip1));
		p2.getItens().addAll(asList(ip3));
		p3.getItens().addAll(asList(ip2));
		
		itemPedidoRepository.saveAll(asList(ip1, ip2, ip3));
	}
}
