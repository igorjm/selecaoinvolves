package com.involves.selecao.service.alerta;

import java.util.List;

import com.involves.selecao.model.alerta.AlertaType;
import com.involves.selecao.model.pesquisa.Pesquisa;
import com.involves.selecao.model.resposta.Resposta;
import com.involves.selecao.service.pesquisa.PesquisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.involves.selecao.model.alerta.Alerta;
import com.involves.selecao.dao.alerta.AlertaDAO;

@Service
public class AlertaServiceImpl implements AlertaService {

    private static final String PERGUNTA_SITUACAO = "Qual a situação do produto?";
    private static final String PERGUNTA_PRECO = "Qual o preço do produto?";
    private static final String PERGUNTA_SHARE = "Qual o share do produto?";
    private static final String RESPOSTA_PRODUTO_AUSENTE = "Produto ausente na gondola";

    @Autowired
    private AlertaDAO alertaDAO;

    @Autowired
    private PesquisaService pesquisaService;

    @Override
    public List<Alerta> buscarTodosAlertas() {
        return alertaDAO.buscarTodosAlertas();
    }

    @Override
    public void processarAlerta() {
        List<Pesquisa> pesquisa = pesquisaService.receberPesquisas();

        try {
            for(Pesquisa listPesquisa : pesquisa) {
                for(Resposta listResposta : listPesquisa.getRespostas()) {
                    String coletado = listResposta.getResposta();
                    String estipulado = listPesquisa.getProduto().getPrecoEstipulado();

                    if(listResposta.getPergunta().equals(PERGUNTA_SITUACAO)) {
                        if(listResposta.equals(RESPOSTA_PRODUTO_AUSENTE)) {
                            salvarAlerta(0, listPesquisa, AlertaType.RUPTURA);
                        }
                    } else {
                        if(listResposta.getPergunta().equals(PERGUNTA_PRECO)) {
                            Integer margem = calcularMargem(coletado, estipulado);

                            if (margem != null && margem != 0) {
                                if (isAboveCollected(coletado, estipulado)) {
                                    salvarAlerta(margem, listPesquisa, AlertaType.PRECO_ACIMA_ESTIPULADO);
                                } else {
                                    salvarAlerta(margem, listPesquisa, AlertaType.PRECO_ABAIXO_ESTIPULADO);
                                }
                            }
                        } else if(listResposta.getPergunta().equals(PERGUNTA_SHARE)) {
                            Integer margem = calcularMargem(coletado, estipulado);

                            if (margem != null && margem != 0) {
                                if (isAboveCollected(coletado, estipulado)) {
                                    salvarAlerta(margem, listPesquisa, AlertaType.SHARE_SUPERIOR_ESTIPULADO);
                                } else {
                                    salvarAlerta(margem, listPesquisa, AlertaType.SHARE_INFERIOR_ESTIPULADO);
                                }
                            }
                        } else {
                            System.out.println("Alerta ainda não implementado!");
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private Integer calcularMargem(String coletado, String estipulado) {
        return Integer.parseInt(coletado) - Integer.parseInt(estipulado);
    }

    private Boolean isAboveCollected(String coletado, String estipulado) {
        if(Integer.parseInt(coletado) > Integer.parseInt(estipulado)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private void salvarAlerta(Integer margem,  Pesquisa pesquisa, AlertaType tipoAlerta) {
        Alerta alerta = new Alerta();
        if (margem != null) {
            alerta.setMargem(margem);
            alerta.setPontoDeVenda(pesquisa.getPontoDeVenda());
            alerta.setDescricao(tipoAlerta.getDescricao());
            alerta.setProduto(pesquisa.getProduto());
            alerta.setValorTipoAlerta(tipoAlerta.getValor());
            alertaDAO.salvar(alerta);
        }
    }
}

