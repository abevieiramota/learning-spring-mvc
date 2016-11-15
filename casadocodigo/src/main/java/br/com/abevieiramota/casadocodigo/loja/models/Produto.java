package br.com.abevieiramota.casadocodigo.loja.models;

public class Produto {

	private String titulo;
	private String descricao;
	private int paginas;

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPaginas() {
		return this.paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	@Override
	public String toString() {
		return "Produto [titulo=" + this.titulo + ", descricao=" + this.descricao + ", paginas=" + this.paginas + "]";
	}

}
