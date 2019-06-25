package com.involves.selecao.model.alerta;

import com.involves.selecao.model.produto.Produto;

public class Alerta {
	
	private String pontoDeVenda;
	private String descricao;
	private Produto produto;
	private AlertaType tipoAlerta;
	private Integer margem;

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

	public Produto getProduto() {
		return produto;
	}
	public String getNomeProduto() {
		return produto != null ? produto.getNome() : "";
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public AlertaType getTipoAlerta() {
		return tipoAlerta;
	}
	public void setValorTipoAlerta(Integer tipoAlerta) {
		this.tipoAlerta.valor = tipoAlerta;
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

}
