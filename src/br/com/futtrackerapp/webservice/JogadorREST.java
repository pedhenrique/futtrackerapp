package br.com.futtrackerapp.webservice;

import java.util.ArrayList;
import java.util.List;

import br.com.futtrackerapp.entidades.Jogador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class JogadorREST {
	//private static final String URL = "http://10.0.3.2:8080/WebService/rest/jogador";
	private static final String URL = "http://200.131.37.199:8080/WebService/rest/jogador";
	//private static final String URL = "http://192.168.25.6:8080/WebService/rest/jogador";

	public static Jogador getJogador(int id) throws Exception {

		String[] resposta = new WebServiceCliente().get(URL + "/"
				+ String.valueOf(id));

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			Jogador jogador = gson.fromJson(resposta[1], Jogador.class);
			return jogador;
		} else {
			throw new Exception(resposta[1]);
		}
	}

	public static List<Jogador> getListaJogador() throws Exception {
		
		String[] resposta = new WebServiceCliente().get(URL);
		// String[] resposta = new WebServiceCliente().get(URL_WS +
		// "buscarTodos");

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<Jogador> listaJogador = new ArrayList<Jogador>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {
				listaJogador.add(gson.fromJson(array.get(i), Jogador.class));
			}
			return listaJogador;
		} else {
			throw new Exception(resposta[1]);
		}
	}
}
