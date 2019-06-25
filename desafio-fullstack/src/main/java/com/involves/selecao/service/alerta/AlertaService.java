package com.involves.selecao.service.alerta;

import com.involves.selecao.model.alerta.Alerta;

import java.io.IOException;
import java.util.List;

public interface AlertaService {

    List<Alerta> buscarTodosAlertas();

    void processarAlerta() throws IOException;
}
