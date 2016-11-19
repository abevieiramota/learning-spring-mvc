package br.com.abevieiramota.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.abevieiramota.casadocodigo.loja.daos.ProdutoDAO;
import br.com.abevieiramota.casadocodigo.loja.infra.FileSaver;
import br.com.abevieiramota.casadocodigo.loja.models.Produto;
import br.com.abevieiramota.casadocodigo.loja.models.TipoPreco;
import br.com.abevieiramota.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	// gerenciado pelo spring
	private ProdutoDAO produtoDao;

	@Autowired
	private FileSaver fileSaver;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}

	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("tipos", TipoPreco.values());
		modelAndView.setViewName("produtos/form");

		return modelAndView;
	}

	// flash attributos > vida entre um atributo e outro
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return form(produto);
		}

		String sumarioPath = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(sumarioPath);

		this.produtoDao.gravar(produto);

		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");

		// 'bug' do F5 -> após post, mandar redirect!
		return new ModelAndView("redirect:/produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = this.produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("produtos", produtos);
		modelAndView.setViewName("produtos/lista");

		return modelAndView;
	}

	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		Produto produto = produtoDao.find(id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("produtos/detalhe");
		modelAndView.addObject("produto", produto);

		return modelAndView;

	}
}
