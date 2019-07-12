package com.involves.selecao.dto.alerta;

import com.involves.selecao.model.alerta.AlertaType;

public class AlertaDTO {

    private String idAlerta;
    private String pontoDeVenda;
    private String descricao;
    private String produto;
    private AlertaType tipoAlerta;
    private Integer margem;

    public String getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(String idAlerta) {
        this.idAlerta = idAlerta;
    }

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

    public Integer getMargem() {
        return margem;
    }

    public void setMargem(Integer margem) {
        this.margem = margem;
    }
}
