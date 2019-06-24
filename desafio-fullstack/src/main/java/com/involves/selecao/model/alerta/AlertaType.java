package com.involves.selecao.model.alerta;

public enum AlertaType {
    RUPTURA(1), PRECO_ACIMA_ESTIPULADO(2), PRECO_ABAIXO_ESTIPULADO(3), SHARE_INFERIOR_ESTIPULADO(4), SHARE_SUPERIOR_ESTIPULADO(5);

    public Integer valor;

    AlertaType(Integer valor) {
        this.valor = valor;
    }
}
