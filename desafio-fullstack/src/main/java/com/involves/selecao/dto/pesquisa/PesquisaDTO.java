package com.involves.selecao.dto.pesquisa;

import com.involves.selecao.model.produto.Produto;

import java.io.Serializable;
import java.util.List;

public class PesquisaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String rotulo;
    private String notificante;
    private String pontoDeVenda;
    private Produto produto;
    private List<RespostaDTO> respostaDTO;
    private String precoEstipulado;
    private String participacaoEstipulada;
    private String categoria;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<RespostaDTO> getRespostaDTO() {
        return respostaDTO;
    }

    public void setRespostaDTO(List<RespostaDTO> respostaDTO) {
        this.respostaDTO = respostaDTO;
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
