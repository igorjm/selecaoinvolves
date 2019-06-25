package com.involves.selecao.model.produto;

public class Produto {

    private long idProduto;
    private String nome;
    private String descricao;
    private String categoria;
    private String precoEstipulado;

    public long getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrecoEstipulado() {
        return precoEstipulado;
    }
    public void setPrecoEstipulado(String precoEstipulado) {
        this.precoEstipulado = precoEstipulado;
    }

}
