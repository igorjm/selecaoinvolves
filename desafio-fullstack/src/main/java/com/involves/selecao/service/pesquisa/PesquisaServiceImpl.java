package com.involves.selecao.service.pesquisa;

import com.google.gson.Gson;
import com.involves.selecao.model.pesquisa.Pesquisa;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class PesquisaServiceImpl implements PesquisaService {

    @Override
    public Pesquisa[] receberPesquisas() {
        Pesquisa[] pesquisa = null;
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
//            System.out.println(content.toString());
            pesquisa =  gson.fromJson(content.toString(), Pesquisa[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(pesquisa);
        return pesquisa;
    }
}
