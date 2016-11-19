package br.com.abevieiramota.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		return this.manager.createQuery("FROM Produto", Produto.class).getResultList();
	}

	public Produto find(Integer id) {
		TypedQuery<Produto> query = this.manager
				.createQuery("SELECT DISTINCT(p) FROM Produto p JOIN FETCH p.precos WHERE p.id = :id", Produto.class);
		query.setParameter("id", id);

		return query.getSingleResult();
	}
}
