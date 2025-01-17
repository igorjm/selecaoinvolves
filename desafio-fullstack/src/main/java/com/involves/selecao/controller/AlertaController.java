package com.involves.selecao.controller;

import java.io.IOException;
import java.util.List;

import com.involves.selecao.model.alerta.Alerta;
import com.involves.selecao.service.alerta.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

	@Autowired
	private AlertaService alertaService;

	@GetMapping("/")
    public List<Alerta> alertas() {
		return alertaService.buscarTodosAlertas();
    }
	
	@GetMapping("/processar")
    public void processar() throws IOException {
		alertaService.processarAlerta();
	}
}
