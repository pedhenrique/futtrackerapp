package br.com.futtrackerapp.webservice;

import java.util.ArrayList;
import java.util.List;

import br.com.futtrackerapp.entidades.Video;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class VideoREST {
	private static final String URL = "http://200.131.37.199:8080/WebService/rest/video";
	
	public static Video getVideo(int id) throws Exception {

		String[] resposta = new WebServiceCliente().get(URL + "/"
				+ String.valueOf(id));

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			Video video = gson.fromJson(resposta[1], Video.class);
			return video;
		} else {
			throw new Exception(resposta[1]);
		}
	}

	public static List<Video> getListaVideo() throws Exception {
		
		String[] resposta = new WebServiceCliente().get(URL);
		// String[] resposta = new WebServiceCliente().get(URL_WS +
		// "buscarTodos");

		if (resposta[0].equals("200")) {
			Gson gson = new Gson();
			ArrayList<Video> listaVideo = new ArrayList<Video>();
			JsonParser parser = new JsonParser();
			JsonArray array = parser.parse(resposta[1]).getAsJsonArray();

			for (int i = 0; i < array.size(); i++) {
				listaVideo.add(gson.fromJson(array.get(i), Video.class));
			}
			return listaVideo;
		} else {
			throw new Exception(resposta[1]);
		}
	}
}

