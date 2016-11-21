package br.com.abevieiramota.casadocodigo.loja.models;

import java.math.BigDecimal;

public class CarrinhoItem {

	private Produto produto;
	private TipoPreco tipoPreco;
	
	public BigDecimal getPreco() {
		return this.produto.precoPara(this.tipoPreco);
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TipoPreco getTipoPreco() {
		return this.tipoPreco;
	}

	public void setTipoPreco(TipoPreco tipoPreco) {
		this.tipoPreco = tipoPreco;
	}

	public CarrinhoItem(Produto produto, TipoPreco tipoPreco) {
		this.produto = produto;
		// TODO Auto-generated constructor stub
		this.tipoPreco = tipoPreco;
	}
	
	public BigDecimal getTotal(int quantidade) {
		return this.getPreco().multiply(new BigDecimal(quantidade));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.produto == null) ? 0 : this.produto.hashCode());
		result = prime * result + ((this.tipoPreco == null) ? 0 : this.tipoPreco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoItem other = (CarrinhoItem) obj;
		if (this.produto == null) {
			if (other.produto != null)
				return false;
		} else if (!this.produto.equals(other.produto))
			return false;
		if (this.tipoPreco != other.tipoPreco)
			return false;
		return true;
	}

	
}
