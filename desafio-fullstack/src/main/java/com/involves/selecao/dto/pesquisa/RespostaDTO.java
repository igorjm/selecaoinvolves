package com.involves.selecao.dto.pesquisa;

import java.io.Serializable;

public class RespostaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String pergunta;
    private String resposta;

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}
