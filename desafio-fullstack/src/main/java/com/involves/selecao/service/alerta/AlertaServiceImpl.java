package com.involves.selecao.service.alerta;

import java.io.IOException;
import java.util.List;

import com.involves.selecao.model.alerta.AlertaType;
import com.involves.selecao.service.pesquisa.PesquisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.involves.selecao.model.alerta.Alerta;
import com.involves.selecao.model.pesquisa.Pesquisa;
import com.involves.selecao.model.pesquisa.Resposta;
import com.involves.selecao.dao.alerta.AlertaDAO;

@Service
public class AlertaServiceImpl implements AlertaService {

    @Autowired
    private AlertaDAO alertaDAO;

    @Autowired
    private PesquisaService pesquisaService;

    @Override
    public List<Alerta> buscarTodosAlertas() {
        return alertaDAO.buscarTodosAlertas();
    }

    @Override
    public void processarAlerta() throws IOException {
        Pesquisa[] pesquisa = pesquisaService.receberPesquisas();

        try {
            for(Pesquisa listPesquisa : pesquisa) {
                for(Resposta listResposta : listPesquisa.getRespostas()) {
                    if(listResposta.getPergunta().equals("Qual a situação do produto?")) {
                        if(listResposta.equals("Produto ausente na gondola")) {
                            salvarAlerta(0, listPesquisa, AlertaType.RUPTURA);
                        }
                    } else if(listResposta.getPergunta().equals("Qual o preço do produto?")) {
                        int precoColetado = Integer.parseInt(listResposta.getResposta());
                        int precoEstipulado = Integer.parseInt(listPesquisa.getProduto().getPrecoEstipulado());
                        int margem = precoEstipulado - Integer.parseInt(listResposta.getResposta());

                        if (margem != 0) {
                            if (precoColetado > precoEstipulado) {
                                salvarAlerta(margem, listPesquisa, AlertaType.PRECO_ACIMA_ESTIPULADO);
                            } else if (precoColetado < precoEstipulado) {
                                salvarAlerta(margem, listPesquisa, AlertaType.PRECO_ABAIXO_ESTIPULADO);
                            }
                        }
                    } else if(listResposta.getPergunta().equals("Qual o share do produto?")) {
                        int participacaoColetada = Integer.parseInt(listResposta.getResposta());
                        int participacaoEstipulada = Integer.parseInt(listPesquisa.getParticipacaoEstipulada());
                        int margem = Integer.parseInt(listPesquisa.getProduto().getPrecoEstipulado()) - Integer.parseInt(listResposta.getResposta());

                        if (margem != 0) {
                            if (participacaoColetada > participacaoEstipulada) {
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
//            for (int i = 0; i < pesquisa.length; i++) {
//                for (int j = 0; j < pesquisa[i].getRespostas().size(); j++) {
//                    Resposta resposta = pesquisa[i].getRespostas().get(j);
//
//                    if (resposta.getPergunta().equals("Qual a situação do produto?")) {
//                        if (resposta.getResposta().equals("Produto ausente na gondola")) {
//                            salvarAlerta(0, pesquisa[i], AlertaType.RUPTURA);
//                        }
//                    } else if (resposta.getPergunta().equals("Qual o preço do produto?")) {
//                        int precoColetado = Integer.parseInt(resposta.getResposta());
//                        int precoEstipulado = Integer.parseInt(pesquisa[i].getProduto().getPrecoEstipulado());
//                        int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());
//
//                        if (precoColetado > precoEstipulado) {
//                            salvarAlerta(margem, pesquisa[i], AlertaType.PRECO_ACIMA_ESTIPULADO);
//                        } else if (precoColetado < precoEstipulado) {
//                            salvarAlerta(margem, pesquisa[i], AlertaType.PRECO_ABAIXO_ESTIPULADO);
//                        }
//                    } else if (resposta.getPergunta().equals("Qual o share do produto?")) {
//                        int participacaoColetada = Integer.parseInt(resposta.getResposta());
//                        int participacaoEstipulada = Integer.parseInt(pesquisa[i].getParticipacaoEstipulada());
//                        int margem = Integer.parseInt(pesquisa[i].getProduto().getPrecoEstipulado()) - Integer.parseInt(resposta.getResposta());
//
//                        if (margem != 0) {
//                            if (participacaoColetada > participacaoEstipulada) {
//                                salvarAlerta(margem, pesquisa[i], AlertaType.SHARE_SUPERIOR_ESTIPULADO);
//                            } else {
//                                salvarAlerta(margem, pesquisa[i], AlertaType.SHARE_INFERIOR_ESTIPULADO);
//                            }
//                        }
//                    } else {
//                        System.out.println("Alerta ainda não implementado!");
//                    }
//                }
//            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void salvarAlerta(Integer margem, Pesquisa pesquisa, AlertaType tipoAlerta) {
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

