package com.involves.selecao.service.pesquisa;

import com.google.gson.Gson;
import com.involves.selecao.model.pesquisa.Pesquisa;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PesquisaServiceImpl implements PesquisaService {

    private static final String URL_PESQUISA = "https://selecao-involves.agilepromoter.com/pesquisas";

    @Override
    public List<Pesquisa> receberPesquisas() {
        Pesquisa[] pesquisa;
        try {
            URL url = new URL(URL_PESQUISA);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer content = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            Gson gson = new Gson();
            pesquisa = gson.fromJson(content.toString(), Pesquisa[].class);
            return Arrays.asList(pesquisa);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Pesquisa>();
    }
}
