package br.com.abevieiramota.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class CarrinhoCompras implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<>();

	public void add(CarrinhoItem item) {
		this.itens.put(item, getQuantidade(item) + 1);
	}

	public int getQuantidade(CarrinhoItem item) {
		if (this.itens.containsKey(item)) {
			return this.itens.get(item);
		}
		return 0;
	}

	public int getQuantidade() {
		return this.itens.values().stream().reduce(0, (p, ac) -> p + ac);
	}
	
	public Collection<CarrinhoItem> getItens() {
		return this.itens.keySet();
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for(CarrinhoItem item: this.itens.keySet()) {
			total = total.add(getTotal(item));
		}
		
		return total;
	}
	
	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}
	
}
