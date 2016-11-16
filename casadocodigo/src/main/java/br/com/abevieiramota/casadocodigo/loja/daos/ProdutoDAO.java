package br.com.abevieiramota.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.abevieiramota.casadocodigo.loja.models.Produto;

@Repository
@Transactional
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(Produto produto) {
		this.manager.persist(produto);
	}

	public List<Produto> listar() {
		return this.manager.createQuery("from Produto", Produto.class).getResultList();
	}
}
