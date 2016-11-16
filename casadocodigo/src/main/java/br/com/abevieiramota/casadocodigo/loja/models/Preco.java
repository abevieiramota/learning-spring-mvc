package br.com.abevieiramota.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Preco {

	private BigDecimal valor;
	private TipoPreco tipo;
	
	public BigDecimal getValor() {
		return this.valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoPreco getTipo() {
		return this.tipo;
	}
	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}
}
