package com.involes.selecao.alerta;

import com.involves.selecao.dto.alerta.AlertaDTO;
import com.involves.selecao.dto.pesquisa.PesquisaDTO;
import com.involves.selecao.model.alerta.Alerta;
import com.involves.selecao.service.alerta.AlertaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlertaTest {

    @Autowired
    private AlertaService alertaService;

    @Test
    public void buscarAlertasTest() {
        List<Alerta> alertas = alertaService.buscarTodosAlertas();
        assertThat(alertas != null);
        assertThat(!alertas.isEmpty());
    }

    @Test
    public void processarAlertasTest() throws IOException {
        alertaService.processarAlerta();
        List<Alerta> alertas = alertaService.buscarTodosAlertas();
        assertThat(alertas != null);
        assertThat(!alertas.isEmpty());
    }

    @Test
    public void processarTest() throws IOException {
        List<PesquisaDTO> pesquisas = new ArrayList<PesquisaDTO>();

        alertaService.processarAlerta();
        List<Alerta> alertas = alertaService.buscarTodosAlertas();
        assertThat(alertas == null);
        assertThat(alertas.isEmpty());
//
//        pesquisas.add(producePesquisaVO());
//        alertaService.processarAlertas(pesquisas);
//        alertas = alertaService.buscarTodos();
//        assertThat(alertas != null);
//        assertThat(!alertas.isEmpty());
//        assertThat(alertas.size() == 1);
    }

//    private PesquisaVO producePesquisaVO() {
//        PesquisaVO pesquisa = new PesquisaVO();
//        pesquisa.setId(1);
//        pesquisa.setRotulo("Campanha de pascoa");
//        pesquisa.setNotificante("João");
//        pesquisa.setPonto_de_venda("Angel One Capoeiras");
//        pesquisa.setProduto("Ovo de Pascoa Kinder 48");
//
//        List<RespostaVO> respostas = new ArrayList<RespostaVO>();
//        RespostaVO respostaVO = new RespostaVO();
//        respostaVO.setPergunta("Qual a situação do produto?");
//        respostaVO.setResposta("Produto está na gondola");
//        respostas.add(respostaVO);
//        pesquisa.setRespostas(respostas);
////		""id":"1","rotulo":"Campanha de pascoa","notificante":"João","ponto_de_venda":"Angel One Capoeiras","produto":"Ovo de Pascoa Kinder 48","respostas":[{"pergunta":"Qual a situação do produto?","resposta":"Produto está na gondola"}";
//        return pesquisa;
//    }

}
