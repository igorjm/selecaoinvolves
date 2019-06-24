package com.involves.selecao.model.pesquisa;
import java.util.List;

public class Pesquisa {
	private int id;
	private String rotulo;
	private String notificante;
	private String pontoDeVenda;
	private String produto;
	private String precoEstipulado;
	private List<Resposta> respostas;
	private String participacaoEstipulada;
	private String categoria;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getRotulo() {
		return rotulo;
	}
	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	public String getNotificante() {
		return notificante;
	}
	public void setNotificante(String notificante) {
		this.notificante = notificante;
	}

	public String getPontoDeVenda() {
		return pontoDeVenda;
	}

	public void setPontoDeVenda(String pontoDeVenda) {
		this.pontoDeVenda = pontoDeVenda;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}
	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}

	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getPrecoEstipulado() {
		return precoEstipulado;
	}
	public void setPrecoEstipulado(String precoEstipulado) {
		this.precoEstipulado = precoEstipulado;
	}

	public String getParticipacaoEstipulada() {
		return participacaoEstipulada;
	}
	public void setParticipacaoEstipulada(String participacaoEstipulada) {
		this.participacaoEstipulada = participacaoEstipulada;
	}

	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
}
