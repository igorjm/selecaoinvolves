package com.involves.selecao.service.AlertaService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.involves.selecao.model.Alerta.Alerta;
import com.involves.selecao.model.Pesquisa.Pesquisa;
import com.involves.selecao.model.Pesquisa.Resposta;
import com.involves.selecao.gateway.AlertaGateway.AlertaGatewayService;

@Service
public class ProcessadorAlertas {

	@Autowired
	private AlertaGatewayService gateway;
	
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

		for (int i = 0; i < ps.length; i++){
			for (int j = 0; j < ps[i].getRespostas().size(); j++){
				Alerta alerta = new Alerta();
				Resposta resposta = ps[i].getRespostas().get(j);
				int margem = precoEstipulado - Integer.parseInt(resposta.getResposta());

				if (resposta.getPergunta().equals("Qual a situação do produto?")) {
					if(resposta.getResposta().equals("Produto ausente na gondola")){
					    alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
					    alerta.setDescricao("Ruptura detectada!");
					    alerta.setProduto(ps[i].getProduto());
					    alerta.setFlTipo(1);
					    gateway.salvar(alerta);
					}
				} else if(resposta.getPergunta().equals("Qual o preço do produto?")) {
					int precoColetado = Integer.parseInt(resposta.getResposta());
					int precoEstipulado = Integer.parseInt(ps[i].getPreco_estipulado());
					if(precoColetado > precoEstipulado){
					    alerta.setMargem(margem);
					    alerta.setDescricao("Preço acima do estipulado!");
					    alerta.setProduto(ps[i].getProduto());
					    alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
					    alerta.setFlTipo(2);
					    gateway.salvar(alerta);
					} else if(precoColetado < precoEstipulado){
					    alerta.setMargem(margem);
					    alerta.setDescricao("Preço abaixo do estipulado!");
					    alerta.setProduto(ps[i].getProduto());
					    alerta.setPontoDeVenda(ps[i].getPonto_de_venda());
					    alerta.setFlTipo(3);
					    gateway.salvar(alerta);
					}
				} else if(resposta.getPergunta().equals("Qual o share do produto?")) {
					if(ps[i].getRespostas.getResposta() > ps.)
				} else {
					System.out.println("Alerta ainda não implementado!");
				}
			} 
		}
	}
}

