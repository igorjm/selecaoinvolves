package com.involves.selecao.model.alerta;

public enum AlertaType {
    RUPTURA("Ruptura detectada!", 1),
    PRECO_ACIMA_ESTIPULADO("Preço acima do estipulado!", 2),
    PRECO_ABAIXO_ESTIPULADO("Preço abaixo do estipulado!", 3),
    SHARE_INFERIOR_ESTIPULADO("Participacao inferior ao estipulado!", 4),
    SHARE_SUPERIOR_ESTIPULADO("Participacao superior ao estipulado!", 5);

    public String descricao;
    public Integer valor;

    AlertaType(String descricao, Integer valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getValor() {
        return valor;
    }
}
