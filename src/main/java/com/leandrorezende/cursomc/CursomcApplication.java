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
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1  = new Produto(null, "Computador", 2000.00);
		Produto p2  = new Produto(null, "Impressora", 800.00);
		Produto p3  = new Produto(null, "Mouse", 80.00);
		Produto p4  = new Produto(null, "Mesa de ecritório", 300.00);
		Produto p5  = new Produto(null, "Toalha", 50.00);
		Produto p6  = new Produto(null, "Colcha", 200.00);
		Produto p7  = new Produto(null, "TV true color", 1200.00);
		Produto p8  = new Produto(null, "Roçadeira", 800.00);
		Produto p9  = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Sampoo", 90.00);

		cat1.getProdutos().addAll(asList(p1, p2, p3));
		cat2.getProdutos().addAll(asList(p2, p4));
		cat3.getProdutos().addAll(asList(p5, p6));
		cat4.getProdutos().addAll(asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(asList(p8));
		cat6.getProdutos().addAll(asList(p9, p10));
		cat7.getProdutos().addAll(asList(p11));
		
		p1.getCategorias().addAll(asList(cat1, cat4));
		p2.getCategorias().addAll(asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(asList(cat1, cat4));
		p4.getCategorias().addAll(asList(cat2));
		p5.getCategorias().addAll(asList(cat3));
		p6.getCategorias().addAll(asList(cat3));
		p7.getCategorias().addAll(asList(cat4));
		p8.getCategorias().addAll(asList(cat5));
		p9.getCategorias().addAll(asList(cat6));
		p10.getCategorias().addAll(asList(cat6));
		p11.getCategorias().addAll(asList(cat7));
		
		categoriaRepository.saveAll(asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
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
