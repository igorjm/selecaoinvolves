package com.involves.selecao.model.alerta;

public class Alerta {
	
	private String pontoDeVenda;
	private String descricao;
	private String produto;
	private AlertaType tipoAlerta;
	private Integer margem;
	private String categoria;
	
	public String getPontoDeVenda() {
		return pontoDeVenda;
	}
	public void setPontoDeVenda(String pontoDeVenda) {
		this.pontoDeVenda = pontoDeVenda;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}

	public AlertaType getTipoAlerta() {
		return tipoAlerta;
	}

	public void setTipoAlerta(AlertaType tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}

	public Integer getMargem(){
		return margem;
	}
	public void setMargem(Integer margem){
		this.margem = margem;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
