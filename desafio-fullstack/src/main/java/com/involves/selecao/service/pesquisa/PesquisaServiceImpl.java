package com.involves.selecao.service.pesquisa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.involves.selecao.dto.pesquisa.PesquisaDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class PesquisaServiceImpl implements PesquisaService {

    @Override
    public List<PesquisaDTO> receberPesquisas() {
        List<PesquisaDTO> pesquisa = null;
        try {
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
            Type listaPesquisaType = new TypeToken<List<PesquisaDTO>>(){}.getType();
            pesquisa =  gson.fromJson(content.toString(), listaPesquisaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pesquisa;

    }
}
