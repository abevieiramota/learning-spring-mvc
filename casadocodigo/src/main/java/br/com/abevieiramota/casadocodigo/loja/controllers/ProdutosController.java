package br.com.abevieiramota.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.abevieiramota.casadocodigo.loja.daos.ProdutoDAO;
import br.com.abevieiramota.casadocodigo.loja.models.Produto;
import br.com.abevieiramota.casadocodigo.loja.models.TipoPreco;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	// gerenciado pelo spring
	private ProdutoDAO produtoDao;

	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("tipos", TipoPreco.values());
		modelAndView.setViewName("produtos/form");

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String gravar(Produto produto) {
		this.produtoDao.gravar(produto);

		return "produtos/ok";
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = this.produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("produtos", produtos);
		modelAndView.setViewName("produtos/lista");

		return modelAndView;
	}
}
