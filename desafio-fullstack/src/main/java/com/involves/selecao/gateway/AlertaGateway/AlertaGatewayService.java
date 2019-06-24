package com.involves.selecao.gateway.AlertaGateway;

import java.util.List;

import com.involves.selecao.alerta.Alerta;

public interface AlertaGatewayService {
	
	void salvar(Alerta alerta);

	List<Alerta> buscarTodos();
}
