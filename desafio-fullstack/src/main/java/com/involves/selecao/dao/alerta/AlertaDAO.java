package com.involves.selecao.dao.alerta;

import java.util.List;

import com.involves.selecao.model.alerta.Alerta;

public interface AlertaDAO {
	
	void salvar(Alerta alerta);

	List<Alerta> buscarTodosAlertas();
}
