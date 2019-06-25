package com.involves.selecao.model.pesquisa;
import com.involves.selecao.model.produto.Produto;

import java.util.List;

public class Pesquisa {
	private int id;
	private String rotulo;
	private String notificante;
	private String pontoDeVenda;
	private Produto produto;
	private List<Resposta> respostas;
	private String participacaoEstipulada;

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

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getParticipacaoEstipulada() {
		return participacaoEstipulada;
	}
	public void setParticipacaoEstipulada(String participacaoEstipulada) {
		this.participacaoEstipulada = participacaoEstipulada;
	}

}
