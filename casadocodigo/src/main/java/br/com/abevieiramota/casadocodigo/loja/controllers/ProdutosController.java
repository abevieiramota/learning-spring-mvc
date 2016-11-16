package br.com.abevieiramota.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.abevieiramota.casadocodigo.loja.daos.ProdutoDAO;
import br.com.abevieiramota.casadocodigo.loja.models.Produto;
import br.com.abevieiramota.casadocodigo.loja.models.TipoPreco;

@Controller
public class ProdutosController {
	
	@Autowired
	// gerenciado pelo spring
	private ProdutoDAO produtoDao;

	@RequestMapping("/produtos/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("tipos", TipoPreco.values());
		modelAndView.setViewName("produtos/form");
		
		return modelAndView;
	}
	
	@RequestMapping("/produtos")
	public String gravar(Produto produto) {
		produtoDao.gravar(produto);
		
		return "produtos/ok";
	}
}
