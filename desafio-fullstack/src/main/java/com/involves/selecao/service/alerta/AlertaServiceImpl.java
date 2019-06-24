package com.involves.selecao.service.alerta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.involves.selecao.model.alerta.AlertaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.model.alerta.Alerta;
import com.involves.selecao.model.pesquisa.Pesquisa;
import com.involves.selecao.model.pesquisa.Resposta;
import com.involves.selecao.dao.alerta.AlertaDAO;

@Service
public class AlertaServiceImpl implements AlertaService{

	@Autowired
	private AlertaDAO alertaDAO;

	@Override
	public List<Alerta> buscarTodosAlertas() {
		return alertaDAO.buscarTodosAlertas();
	}

	@Override
	public void processa() throws IOException {
		URL url = new URL("https://selecao-involves.agilepromoter.com/pesquisas");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(
		  new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer content = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();

		Gson gson = new Gson();
		Pesquisa[] ps =  gson.fromJson(content.toString(), Pesquisa[].class);

		try {
			for (int i = 0; i < ps.length; i++){
                for (int j = 0; j < ps[i].getRespostas().size(); j++){
                    Alerta alerta = new Alerta();
                    Resposta resposta = ps[i].getRespostas().get(j);

                    if(resposta.getPergunta().equals("Qual a situação do produto?")) {
                        if(resposta.getResposta().equals("Produto ausente na gondola")){
                            alerta.setPontoDeVenda(ps[i].getPontoDeVenda());
                            alerta.setDescricao("Ruptura detectada!");
                            alerta.setProduto(ps[i].getProduto());
                            alerta.setTipoAlerta(AlertaType.RUPTURA);
                            alertaDAO.salvar(alerta);
                        }
                    } else if(resposta.getPergunta().equals("Qual o preço do produto?")) {
                        int precoColetado = Integer.parseInt(resposta.getResposta());
                        int precoEstipulado = Integer.parseInt(ps[i].getPrecoEstipulado());

                        int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());

                        if(precoColetado > precoEstipulado){
                            alerta.setMargem(margem);
                            alerta.setDescricao("Preço acima do estipulado!");
                            alerta.setProduto(ps[i].getProduto());
                            alerta.setPontoDeVenda(ps[i].getPontoDeVenda());
                            alerta.setTipoAlerta(AlertaType.PRECO_ACIMA_ESTIPULADO);
                            alertaDAO.salvar(alerta);
                        } else if(precoColetado < precoEstipulado){
                            alerta.setMargem(margem);
                            alerta.setDescricao("Preço abaixo do estipulado!");
                            alerta.setProduto(ps[i].getProduto());
                            alerta.setPontoDeVenda(ps[i].getPrecoEstipulado());
                            alerta.setTipoAlerta(AlertaType.PRECO_ABAIXO_ESTIPULADO);
                            alertaDAO.salvar(alerta);
                        }
                    } else if(resposta.getPergunta().equals("Qual o share do produto?")) {
                        int participacaoColetada = Integer.parseInt(resposta.getResposta());
                        int participacaoEstipulada = Integer.parseInt(ps[i].getParticipacaoEstipulada());

                        int margem = Integer.parseInt(ps[i].getPrecoEstipulado()) - Integer.parseInt(resposta.getResposta());

                        if(margem != 0) {
                            alerta.setMargem(margem);
                            alerta.setDescricao(participacaoColetada > participacaoEstipulada ? "Participacao superior ao estipulado" : "Participacao inferior ao estipulado");
                            alerta.setProduto(ps[i].getProduto());
                            alerta.setCategoria(ps[i].getCategoria());
                            alerta.setTipoAlerta(participacaoColetada > participacaoEstipulada ? AlertaType.SHARE_SUPERIOR_ESTIPULADO : AlertaType.SHARE_INFERIOR_ESTIPULADO);
                            alertaDAO.salvar(alerta);
                        }

                    } else {
                        System.out.println("Alerta ainda não implementado!");
                    }
                }
            }
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}

