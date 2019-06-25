package com.involves.selecao.dao.alerta;

import java.util.ArrayList;
import java.util.List;

import com.involves.selecao.model.alerta.AlertaType;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.involves.selecao.model.alerta.Alerta;
import com.involves.selecao.gateway.mongo.MongoDbFactory;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class AlertaDAOImpl implements AlertaDAO {
	
	@Autowired
	private MongoDbFactory mongoFactory;

	@Override
	public void salvar(Alerta alerta) {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("Alertas");

		Document doc = new Document("ponto_de_venda", alerta.getPontoDeVenda())
                .append("descricao", alerta.getDescricao())
                .append("tipo", alerta.getTipoAlerta().valor)
                .append("margem", alerta.getMargem())
                .append("produto", alerta.getProduto());
		collection.insertOne(doc);
	}

	@Override
	public List<Alerta> buscarTodosAlertas() {
		MongoDatabase database = mongoFactory.getDb();
		MongoCollection<Document> collection = database.getCollection("Alertas");
		FindIterable<Document> db = collection.find();
		List<Alerta> alertas = new ArrayList<>();

		for (Document document : db) {
			Alerta alerta = new Alerta();
			alerta.setDescricao(document.getString("descricao"));
			alerta.setTipoAlerta(AlertaType.valueOf(document.getString("tipo")));
			alerta.setMargem(document.getInteger("margem"));
			alerta.setPontoDeVenda(document.getString("ponto_de_venda"));
//			alerta.setProduto(document.getString("produto"));
			alertas.add(alerta);
		}

		return alertas;
	}
}
