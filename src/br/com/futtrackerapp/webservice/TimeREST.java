package br.com.futtrackerapp.webservice;

import java.util.ArrayList;
import java.util.List;

import br.com.futtrackerapp.entidades.Time;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class TimeREST {
	//private static final String URL = "http://10.0.3.2:8080/WebService/rest/time";
	private static final String URL = "http://200.131.37.199:8080/WebService/rest/time";
	//private static final String URL = "http://192.168.25.6:8080/WebService/rest/time";


	public static Time getTime(int id) throws Exception {

		String[] resposta = new WebServiceCliente().get(URL + "/"
				+ String.valueOf(id));

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			Time time = gson.fromJson(resposta[1], Time.class);
			return time;
		} else {
			throw new Exception(resposta[1]);
		}
	}

	public static List<Time> getListaTime() throws Exception {
		
		String[] resposta = new WebServiceCliente().get(URL);
		// String[] resposta = new WebServiceCliente().get(URL_WS +
		// "buscarTodos");

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<Time> listaTime = new ArrayList<Time>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {
				listaTime.add(gson.fromJson(array.get(i), Time.class));
			}
			return listaTime;
		} else {
			throw new Exception(resposta[1]);
		}
	}
}
