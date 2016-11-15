package br.com.abevieiramota.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.abevieiramota.casadocodigo.loja.daos.ProdutoDAO;
import br.com.abevieiramota.casadocodigo.loja.models.Produto;

@Controller
public class ProdutosController {
	
	@Autowired
	// gerenciado pelo spring
	private ProdutoDAO produtoDao;

	@RequestMapping("/produtos/form")
	public String form() {
		return "produtos/form";
	}
	
	@RequestMapping("/produtos")
	public String gravar(Produto produto) {
		produtoDao.gravar(produto);
		
		return "produtos/ok";
	}
}
