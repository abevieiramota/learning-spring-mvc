package br.com.abevieiramota.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.abevieiramota.casadocodigo.loja.models.Produto;

@Repository
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public void gravar(Produto produto) {
		this.manager.persist(produto);
	}
}
